package org.sonar.cxx;

import org.sonar.api.config.internal.MapSettings;
import org.sonar.api.resources.Language;
import org.sonar.api.scanner.ScannerSide;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.cxx.checks.*;
import org.sonar.cxx.squidbridge.annotations.AnnotationBasedRulesDefinition;
import org.sonar.plugins.cxx.CustomCxxRulesDefinition;
import org.sonar.plugins.cxx.CxxLanguage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@ScannerSide
public class MyCustomRulesDefinition extends CustomCxxRulesDefinition {
  private static final Language LANGUAGE = new CxxLanguage(new MapSettings().asConfig());

  @Override
  public Language getLanguage() {
    return LANGUAGE;
  }

  @Override
  public String repositoryName() {
    return "Custom Repository";
  }

  @Override
  public String repositoryKey() {
    return "customrepo";
  }

  @SuppressWarnings("rawtypes")
  @Override
  public Class[] checkClasses() {
    return new Class[] { UsingNamespaceCheck.class };
  }
  @Override
  public void define(RulesDefinition.Context context) {
    var repo = context.createRepository(repositoryKey(), getLanguage().getKey())
            .setName(repositoryName());

    // Load metadata from check classes' annotations
    new AnnotationBasedRulesDefinition(repo, getLanguage().getKey()).addRuleClasses(false,
            Arrays.asList(checkClasses()));

    // Optionally override html description from annotation with content from html files
    repo.rules().forEach(rule -> rule.setHtmlDescription(loadResource("/org/sonar/l10n/cxx/rules/cxx/" + rule.key() + ".html")));
    repo.done();
  }

  private String loadResource(String path) {
    URL resource = getClass().getResource(path);
    if (resource == null) {
      throw new IllegalStateException("Resource not found: " + path);
    }
    ByteArrayOutputStream result = new ByteArrayOutputStream();
    try (InputStream in = resource.openStream()) {
      byte[] buffer = new byte[1024];
      for (int len = in.read(buffer); len != -1; len = in.read(buffer)) {
        result.write(buffer, 0, len);
      }
      return new String(result.toByteArray(), StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new IllegalStateException("Failed to read resource: " + path, e);
    }
  }
}
