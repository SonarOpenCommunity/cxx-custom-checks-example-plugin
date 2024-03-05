package org.sonar.cxx;

import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.internal.SensorContextTester;

public class CxxFileTester {

  public InputFile cxxFile;
  public SensorContextTester context;

  public InputFile asInputFile() {
    return cxxFile;
  }

}
