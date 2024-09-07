package delta.common.utils.environment;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test for system properties. 
 * @author DAM
 */
@DisplayName("Test system properties")
class TestSystemProperties
{
  private static final String[][] PROPERTIES={
    {"java.version","Java Runtime Environment version"},
    {"java.vendor","Java Runtime Environment vendor"},
    {"java.vendor.url","Java vendor URL"},
    {"java.home","Java installation directory"},
    {"java.vm.specification.version","Java Virtual Machine specification version"},
    {"java.vm.specification.vendor","Java Virtual Machine specification vendor"},
    {"java.vm.specification.name","Java Virtual Machine specification name"},
    {"java.vm.version","Java Virtual Machine implementation version"},
    {"java.vm.vendor","Java Virtual Machine implementation vendor"},
    {"java.vm.name","Java Virtual Machine implementation name"},
    {"java.specification.version","Java Runtime Environment specification version"},
    {"java.specification.vendor","Java Runtime Environment specification vendor"},
    {"java.specification.name","Java Runtime Environment specification name"},
    {"java.class.version","Java class format version number"},
    {"java.class.path","Java class path"},
    {"java.library.path","List of paths to search when loading libraries"},
    {"java.io.tmpdir","Default temp file path"},
    {"java.compiler","Name of JIT compiler to use"},
    {"java.ext.dirs","Path of extension directory or directories"},
    {"os.name","Operating system name"},
    {"os.arch","Operating system architecture"},
    {"os.version","Operating system version"},
    {"file.separator","File separator (\"/\" on UNIX)"},
    {"path.separator","Path separator (\":\" on UNIX)"},
    {"line.separator","Line separator (\"\\n\" on UNIX)"},
    {"user.name","User's account name"},
    {"user.home","User's home directory"},
    {"user.dir","User's current working directory"}
  };

  /**
   * Test access to system properties.
   */
  @Test
  void testProperties()
  {
    Set<String> properties=new HashSet<String>();
    String propertyName;
    String propertyValue;
    String propertySemantics;
    for(int i=0;i<PROPERTIES.length;i++)
    {
      propertyName=PROPERTIES[i][0];
      propertySemantics=PROPERTIES[i][1];
      propertyValue=System.getProperty(propertyName);
      System.out.println(propertyName+" ("+propertySemantics+") = ["+propertyValue+"]");
      properties.add(propertyName);
    }
    Properties systemProperties=System.getProperties();
    Enumeration<Object> keys=systemProperties.keys();
    Object element;
    Object value;
    while (keys.hasMoreElements())
    {
      element=keys.nextElement();
      if (!properties.contains(element))
      {
        value=systemProperties.get(element);
        System.out.println("* "+element+"=["+value+"]");
      }
    }
  }
}
