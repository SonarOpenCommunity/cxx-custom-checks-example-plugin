package org.sonar.cxx;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.sonar.api.Plugin;
import org.sonar.api.SonarEdition;
import org.sonar.api.SonarQubeSide;
import org.sonar.api.SonarRuntime;
import org.sonar.api.config.internal.MapSettings;
import org.sonar.api.internal.PluginContextImpl;
import org.sonar.api.internal.SonarRuntimeImpl;
import org.sonar.api.utils.Version;

public class MyCustomRulesPluginTest {

  @Test
  public void test() {
    SonarRuntime runtime = SonarRuntimeImpl.forSonarQube(Version.create(8, 9),
                                                         SonarQubeSide.SCANNER,
                                                         SonarEdition.COMMUNITY);
    MapSettings settings = new MapSettings().setProperty("foo", "bar");
    Plugin.Context context = new PluginContextImpl.Builder()
      .setSonarRuntime(runtime)
      .setBootConfiguration(settings.asConfig())
      .build();

    MyCustomRulesPlugin plugin = new MyCustomRulesPlugin();
    plugin.define(context);
    assertThat(context.getExtensions().size()).isEqualTo(1);
  }

}
