package org.sonar.cxx;

import java.util.List;

import org.sonar.api.SonarPlugin;

import com.google.common.collect.ImmutableList;

public class MyCustomRulesPlugin extends SonarPlugin {
  
  @SuppressWarnings("rawtypes")
  @Override
  public List getExtensions() {
    return ImmutableList.of(MyCustomRulesDefinition.class);
  }
}
