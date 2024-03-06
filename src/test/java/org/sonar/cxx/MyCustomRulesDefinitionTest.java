package org.sonar.cxx;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import org.sonar.cxx.MyCustomRulesDefinition;

public class MyCustomRulesDefinitionTest {

  @Test
  public void test() {
    MyCustomRulesDefinition definition = new MyCustomRulesDefinition();
    assertThat(definition.repositoryName()).isEqualTo("Custom Repository");
    assertThat(definition.repositoryKey()).isEqualTo("customrepo");
    assertThat(definition.checkClasses().length).isEqualTo(1);
  }

}
