package delta.common.utils.files;

import java.io.File;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import delta.common.utils.environment.User;

/**
 * Unit test class for the text file writer.
 * @author DAM
 */
@DisplayName("Text file writer test")
class TestTextFileWriter
{
  /**
   * Test a simple basic usage of the text file writer.
   */
  @Test
  void testWriteSimpleFile()
  {
    User user=User.getLocalUser();
    File userHome=user.getHomeDir();
    File f=new File(userHome,"testTextFileWriter.txt");
    TextFileWriter t=new TextFileWriter(f);
    t.start();
    t.writeNextLine("My line");
    t.terminate();
    f.delete();
  }
}
