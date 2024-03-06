package org.sonar.cxx;

import org.sonar.cxx.checks.UsingNamespaceCheck;
import org.sonar.plugins.cxx.CustomCxxRulesDefinition;

public class MyCustomRulesDefinition extends CustomCxxRulesDefinition {

  @Override
  public String repositoryName() {
    return "MyCustomCxxRepository";
  }

  @Override
  public String repositoryKey() {
    return "mycustomcxxrepo";
  }

  @SuppressWarnings("rawtypes")
  @Override
  public Class[] checkClasses() {
    return new Class[]{
      UsingNamespaceCheck.class
    };
  }

}
