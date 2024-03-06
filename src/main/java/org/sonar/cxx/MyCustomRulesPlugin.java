package org.sonar.cxx;

import org.sonar.api.Plugin;
import org.sonar.api.Plugin.Context;

public final class MyCustomRulesPlugin implements Plugin {

  @Override
  public void define(Context context) {
    context.addExtension(
      MyCustomRulesDefinition.class
    );
  }
}
