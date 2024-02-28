package org.sonar.cxx;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import org.sonar.api.Plugin;
import org.sonar.api.SonarEdition;
import org.sonar.api.SonarQubeSide;
import org.sonar.api.SonarRuntime;
import org.sonar.api.internal.SonarRuntimeImpl;
import org.sonar.api.utils.Version;

public class MyCustomRulesPluginTest {

  @Test
  public void test() {
    SonarRuntime runtime = SonarRuntimeImpl.forSonarQube(
            Version.create(8, 6),
            SonarQubeSide.SCANNER,
            SonarEdition.COMMUNITY
    );
    Plugin.Context context = new Plugin.Context(runtime);
    MyCustomRulesPlugin plugin = new MyCustomRulesPlugin();
    plugin.define(context);
    assertThat(context.getExtensions().size()).isEqualTo(1);
  }

}
