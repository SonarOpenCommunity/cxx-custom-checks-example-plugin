package org.sonar.cxx;

import org.sonar.cxx.checks.UsingNamespaceCheck;
import org.sonar.plugins.cxx.api.CustomCxxRulesDefinition;

public class MyCustomRulesDefinition extends CustomCxxRulesDefinition {

  @Override
  public String repositoryName() {
    return "Repository";
  }

  @Override
  public String repositoryKey() {
    return "repo";
  }

  @SuppressWarnings("rawtypes")
  @Override
  public Class[] checkClasses() {
    return new Class[] { UsingNamespaceCheck.class };
  }

}
