package org.sonar.cxx;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MyCustomRulesDefinitionTest {

  @Test
  public void test() {
    MyCustomRulesDefinition definition = new MyCustomRulesDefinition();
    assertThat(definition.repositoryName()).isEqualTo("Custom CXX");
    assertThat(definition.repositoryKey()).isEqualTo("mycxx");
    assertThat(definition.checkClasses().length).isEqualTo(1);
  }

}
