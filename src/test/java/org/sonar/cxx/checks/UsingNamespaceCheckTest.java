package org.sonar.cxx.checks;

import org.junit.Test;
import org.sonar.cxx.checks.UsingNamespaceCheck;
import org.sonar.cxx.CxxAstScanner;
import org.sonar.cxx.squidbridge.api.SourceFile;
import org.sonar.cxx.squidbridge.checks.CheckMessagesVerifier;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class UsingNamespaceCheckTest {

  @Test
  public void check() throws UnsupportedEncodingException, IOException {
    UsingNamespaceCheck usingNamespaceCheck = new UsingNamespaceCheck();

    CxxFileTester testerHelper = CxxFileTesterHelper.create("src/test/resources/checks/UsingNamespaceCheck.cc", ".");
    SourceFile sourceFile = CxxAstScanner.scanSingleInputFile(testerHelper.asInputFile(), usingNamespaceCheck);

    CheckMessagesVerifier.verify(sourceFile.getCheckMessages())
      .next().atLine(1).withMessage("Using namespace are not allowed.")
      .noMore();
  }
}
