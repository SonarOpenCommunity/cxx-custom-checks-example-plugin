package org.sonar.cxx;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import org.sonar.cxx.MyCustomRulesDefinition;

public class MyCustomRulesDefinitionTest {

  @Test
  public void test() {
    MyCustomRulesDefinition definition = new MyCustomRulesDefinition();
    assertThat(definition.repositoryName()).isEqualTo("Repository");
    assertThat(definition.repositoryKey()).isEqualTo("repo");
    assertThat(definition.checkClasses().length).isEqualTo(1);
  }

}
