package org.sonar.cxx;
import org.sonar.api.Plugin;

public class MyCustomRulesPlugin implements Plugin {

  @Override
  public void define(Context context) {
    context.addExtension(
            MyCustomRulesDefinition.class
    );
  }

}
