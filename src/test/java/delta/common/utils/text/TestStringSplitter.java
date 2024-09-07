package delta.common.utils.text;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test for the string splitter.
 * @author DAM
 */
@DisplayName("String splitter test")
class TestStringSplitter
{
  /**
   * Test string splitting.
   */
  @Test
  void testStringSplitter()
  {
    String[] samples= {"",",",",,",",,,",",adsd,bdff,","dfgf","dfgf,dds"};
    char[] separators= {',',',',',',',',',',',',','};
    String[][] expectedResults= {
      {}, {"",""}, {"","",""},{"","","",""},
      {"","adsd","bdff",""},
      { "dfgf" },
      { "dfgf", "dds" }
    };
    assertEquals(samples.length,separators.length);
    int nb=samples.length;

    for(int i=0;i<nb;i++)
    {
      // Test "array" version
      {
        String[] ret=StringSplitter.split(samples[i],separators[i]);
        if (expectedResults[i]==null)
        {
          assertNull(ret);
        }
        else
        {
          assertNotNull(ret);
          int nbParts=ret.length;
          assertEquals(expectedResults[i].length,nbParts);
          for(int j=0;j<nbParts;j++)
          {
            assertEquals(expectedResults[i][j],ret[j]);
          }
        }
      }
      // Test "list" version
      {
        List<String> ret=StringSplitter.splitAsList(samples[i],separators[i]);
        if (expectedResults[i]==null)
        {
          assertNull(ret);
        }
        else
        {
          assertNotNull(ret);
          int nbParts=ret.size();
          assertEquals(expectedResults[i].length,nbParts);
          for(int j=0;j<nbParts;j++)
          {
            assertEquals(expectedResults[i][j],ret.get(j));
          }
        }
      }
    }
  }
}
