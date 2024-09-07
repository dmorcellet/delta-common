package delta.common.utils.xml.sax;

import java.net.URL;

import org.junit.jupiter.api.Test;

import delta.common.utils.io.streams.IndentableStream;
import delta.common.utils.url.URLTools;
import delta.common.utils.xml.SAXParsingTools;
import delta.common.utils.xml.sax.pojos.MainPojo;


/**
 * Test class for the SAX parser engine.
 * @author DAM
 */
public class SAXParserEngineTest
{
  /**
   * Test the SAX parser engine.
   */
  
  @Test
  void testEngine()
  {
    SAXParserEngine<MainPojo> engine=new SAXParserEngine<>(new MainParser());
    URL url=URLTools.getFromClassPath("sample.xml",getClass());
    MainPojo result=SAXParsingTools.parseFromURL(url,engine);
    IndentableStream out=new IndentableStream(System.out);
    result.dump(out);
  }
}
