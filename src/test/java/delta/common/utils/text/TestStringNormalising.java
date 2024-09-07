package delta.common.utils.text;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test for the normalizing strings.
 * @author sgronlun
 */
@DisplayName("String normalizing test")
class TestStringNormalising
{
  /**
   * Test string normalizing
   */
  @Test
  void testStringNormalizing()
  {
    // Chars obtained from quests.xml plus a few more
    String utf8string="!\"#$%&'()*+,-./0123456789:;<=>?"
        +"ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]_"
        +"abcdefghijklmnopqrstuvwxyz{|}~"
        +"ÁÂÉÊËÍÎÓÔÖÚÛÅÄÖàáâäéêëíîòóôõöùúû"
        +"–—‘’“”•…‰";
    String expected="!\"#$%&'()*+,-./0123456789:;<=>?"
        +"ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]_"
        +"abcdefghijklmnopqrstuvwxyz{|}~"
        +"AAEEEIIOOOUUAAOaaaaeeeiiooooouuu"
        +"–—‘’“”•...‰";

    String norm=StringFilter.normalize(utf8string);
    assertEquals(norm,expected);
  }
}
