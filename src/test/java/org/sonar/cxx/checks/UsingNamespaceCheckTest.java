package org.sonar.cxx.checks;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;
import org.sonar.cxx.CxxAstScanner;
import org.sonar.cxx.CxxFileTesterHelper;
import org.sonar.cxx.squidbridge.api.SourceFile;
import org.sonar.cxx.squidbridge.checks.CheckMessagesVerifier;

public class UsingNamespaceCheckTest {

  @Test
  public void check() throws UnsupportedEncodingException, IOException {
    UsingNamespaceCheck check = new UsingNamespaceCheck();

    var tester = CxxFileTesterHelper.create("src/test/resources/checks/UsingNamespaceCheck.cc", ".");
    SourceFile file = CxxAstScanner.scanSingleInputFile(tester.asInputFile(), check);

    CheckMessagesVerifier.verify(file.getCheckMessages())
      .next().atLine(1).withMessage("Using namespace are not allowed.")
      .noMore();
  }
}
