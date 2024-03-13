package org.sonar.cxx;

import org.sonar.cxx.checks.UsingNamespaceCheck;
import org.sonar.plugins.cxx.CustomCxxRulesDefinition;

public class MyCustomRulesDefinition extends CustomCxxRulesDefinition {

  @Override
  public String repositoryName() {
    return "Custom CXX";
  }

  @Override
  public String repositoryKey() {
    // The html descriptions for the rules of repository must be stored in the path '/org/sonar/l10n/cxx/rules/mycxx'.
    // If the return value of 'repositoryKey' is changed, the storage location in 'resources' must also be adjusted.
    return "mycxx";
  }

  @SuppressWarnings("rawtypes")
  @Override
  public Class[] checkClasses() {
    return new Class[]{
      UsingNamespaceCheck.class
    };
  }

}
