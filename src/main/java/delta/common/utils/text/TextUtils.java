package delta.common.utils.text;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import delta.common.utils.files.TextFileReader;
import delta.common.utils.misc.MiscStringConstants;

/**
 * A collection of tool methods related to text management.
 * @author DAM
 */
public class TextUtils
{
  private static final Logger LOGGER=LoggerFactory.getLogger(TextUtils.class);

  /**
   * Reads the contents of a text file as a string.
   * @param path path of file to read.
   * @param encoding Encoding to use.
   * @return a <tt>String</tt> that reflects the contents of the given file or <code>null</code> if an error occurred.
   */
  public static String loadTextFile(File path, String encoding)
  {
    List<String> lines=readAsLines(path,encoding);
    return concatLines(lines);
  }

  /**
   * Reads the contents of a text resource as a string.
   * @param url URL to read.
   * @param encoding Encoding to use.
   * @return a <tt>String</tt> that reflects the contents of the given resource or <code>null</code> if an error occurred.
   */
  public static String loadText(URL url, String encoding)
  {
    TextFileReader r=new TextFileReader(url,encoding);
    List<String> lines=readAsLines(r);
    return concatLines(lines);
  }

  /**
   * Concatenate lines into a single string.
   * @param lines Lines to use.
   * @return concatenated string.
   */
  public static String concatLines(List<String> lines)
  {
    String ret=null;
    if ((lines!=null) && (lines.size()>0))
    {
      StringBuilder sb=new StringBuilder();
      for(String line : lines)
      {
        sb.append(line);
        sb.append(MiscStringConstants.NATIVE_EOL);
      }
      ret=sb.toString();
    }
    return ret;
  }

  /**
   * Reads the contents of a text file as a series of lines.
   * @param path path of file to read.
   * @return a <tt>List</tt> of <tt>String</tt>s that reflects the contents of the given file or <code>null</code> if an error occurred.
   */
  public static List<String> readAsLines(File path)
  {
    return readAsLines(path,null);
  }

  /**
   * Reads the contents of a text file as a series of lines.
   * @param path path of file to read.
   * @param encoding Encoding to use.
   * @return a <tt>List</tt> of <tt>String</tt>s that reflects the contents of the given file
   * or <code>null</code> if an error occurred.
   */
  public static List<String> readAsLines(File path, String encoding)
  {
    TextFileReader reader=new TextFileReader(path,encoding);
    List<String> ret=readAsLines(reader);
    return ret;
  }

  /**
   * Reads the contents of a text source as a series of lines.
   * @param reader Text reader.
   * @return a <tt>List</tt> of <tt>String</tt>s that reflects the contents of the given source
   * or <code>null</code> if an error occurred.
   */
  public static List<String> readAsLines(TextFileReader reader)
  {
    List<String> ret=null;
    try
    {
      if (reader.start())
      {
        ret=new ArrayList<String>();
        String line;
        while(true)
        {
          line=reader.getNextLine();
          if (line!=null)
          {
            ret.add(line);
          }
          else
          {
            break;
          }
        }
        reader.terminate();
      }
    }
    catch(Exception e)
    {
      LOGGER.error("Error while reading text file contents",e);
    }
    return ret;
  }
}
