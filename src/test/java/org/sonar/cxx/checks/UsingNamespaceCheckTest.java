package org.sonar.cxx.checks;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.junit.Test;
import org.sonar.cxx.CxxAstScanner;
import org.sonar.squidbridge.api.SourceFile;
import org.sonar.squidbridge.checks.CheckMessagesVerifier;

public class UsingNamespaceCheckTest {

  @Test
  public void check() throws UnsupportedEncodingException, IOException {
    UsingNamespaceCheck check = new UsingNamespaceCheck();

    CxxFileTester tester = CxxFileTesterHelper.CreateCxxFileTester("src/test/resources/checks/UsingNamespaceCheck.cc", ".");
    SourceFile file = CxxAstScanner.scanSingleFile(tester.cxxFile, tester.sensorContext, check);

    CheckMessagesVerifier.verify(file.getCheckMessages())
      .next().atLine(1).withMessage("Using namespace are not allowed.")
      .noMore();
  }
}
