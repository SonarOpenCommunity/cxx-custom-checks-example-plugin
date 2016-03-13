package org.sonar.cxx;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import org.sonar.cxx.MyCustomRulesPlugin;

public class MyCustomRulesPluginTest {

  @Test
  public void test() {
    MyCustomRulesPlugin plugin = new MyCustomRulesPlugin();
    assertThat(plugin.getExtensions().size()).isEqualTo(1);
  }

}
