package delta.common.utils.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import delta.common.utils.environment.FileSystem;
import delta.common.utils.files.TextFileWriter;


/**
 * Tests for the configuration service.
 * @author DAM
 */
@DisplayName("Configuration test")
class TestConfiguration
{
  private static final String IF_EXISTS_SECTION="TEST_IF_EXISTS";
  private static final String FLAG_VAR="FLAG";
  private static final String OTHER_FLAG_VAR="OTHER_FLAG";
  private static final String VALUE1_VAR="VALUE1";
  private static final String VALUE1="v1";
  private static final String VALUE2_VAR="VALUE2";
  private static final String VALUE2="vé";

  /**
   * Test the "get user configuration" feature.
   */
  @Test
  void testGetUserConfiguration()
  {
    Configuration c=Configurations.getUserConfiguration();
    assertNotNull(c);
  }

  /**
   * Tests the "if exists" feature.
   */
  @Test
  void testIfExists()
  {
    File cfgFile=buildIfExistsTestFile();
    Configuration cfg=ConfigurationFileIO.loadFile(cfgFile);
    assertNotNull(cfg);
    boolean resolution=cfg.resolveValues();
    assertTrue(resolution);
    String v1=cfg.getStringValue(IF_EXISTS_SECTION, VALUE1_VAR, null);
    assertNotNull(v1);
    assertEquals(VALUE1,v1);
    String v2=cfg.getStringValue(IF_EXISTS_SECTION, VALUE2_VAR, null);
    assertNotNull(v2);
    assertEquals(VALUE2,v2);
    cfgFile.delete();
  }

  /**
   * Build a test configuration file for the "if exists" test.
   * @return The path of the newly built file.
   */
  private File buildIfExistsTestFile()
  {
    File tmp=FileSystem.getTmpDir();
    File cfgFile=new File(tmp,"ifExists.cfg");
    TextFileWriter writer=new TextFileWriter(cfgFile);
    if (writer.start())
    {
      writer.writeNextLine("["+IF_EXISTS_SECTION+"]");
      writer.writeNextLine(FLAG_VAR+"=true");
      writer.writeNextLine(VALUE1_VAR+"=${"+Configuration.IF_EXISTS+"|"+IF_EXISTS_SECTION+","+FLAG_VAR+","+VALUE1+","+VALUE2+"}");
      writer.writeNextLine(VALUE2_VAR+"=${"+Configuration.IF_EXISTS+"|"+IF_EXISTS_SECTION+","+OTHER_FLAG_VAR+","+VALUE1+","+VALUE2+"}");
      writer.terminate();
    }
    return cfgFile;
  }

  /**
   * Test the "dump configuration" feature.
   */
  @Test
  void testDump()
  {
    Configurations.getUserConfiguration().dump(System.out);
  }
}
