package org.sonar.cxx;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.input.BOMInputStream;
import org.sonar.api.batch.fs.internal.DefaultInputFile;
import org.sonar.api.batch.fs.internal.TestInputFileBuilder;
import org.sonar.api.batch.sensor.internal.SensorContextTester;

public final class CxxFileTesterHelper {

  private CxxFileTesterHelper() {
    // utility class
  }

  public static CxxFileTester create(String fileName, String basePath)
    throws UnsupportedEncodingException, IOException {
    return create(fileName, basePath, Charset.defaultCharset());
  }

  public static CxxFileTester create(String fileName, String basePath, Charset charset)
    throws UnsupportedEncodingException, IOException {
    var tester = new CxxFileTester();

    tester.context = SensorContextTester.create(new File(basePath));
    tester.cxxFile = createInputFile(fileName, basePath, charset);
    tester.context.fileSystem().add(tester.cxxFile);

    return tester;
  }

  private static DefaultInputFile createInputFile(String fileName, String basePath, Charset charset) throws IOException {
    var fb = TestInputFileBuilder.create("", fileName);

    fb.setCharset(charset);
    fb.setProjectBaseDir(Path.of(basePath));
    fb.setContents(getSourceCode(Path.of(basePath, fileName).toFile(), charset));

    return fb.build();
  }

  private static String getSourceCode(File filename, Charset defaultCharset) throws IOException {
    try (var bomInputStream = new BOMInputStream(new FileInputStream(filename),
                                             ByteOrderMark.UTF_8,
                                             ByteOrderMark.UTF_16LE,
                                             ByteOrderMark.UTF_16BE,
                                             ByteOrderMark.UTF_32LE,
                                             ByteOrderMark.UTF_32BE)) {
      ByteOrderMark bom = bomInputStream.getBOM();
      Charset charset = bom != null ? Charset.forName(bom.getCharsetName()) : defaultCharset;
      byte[] bytes = bomInputStream.readAllBytes();
      return new String(bytes, charset);
    }
  }
}
