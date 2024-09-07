package delta.common.utils.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test for class {@link Range}.
 * @author DAM
 */
class RangeTest
{
  /**
   * Test method for {@link delta.common.utils.math.Range#getMin()}.
   */
  @Test
  void testGetMin()
  {
    // Empty range
    Range r1=new Range();
    assertEquals(r1.getMin(),null);
    // Minimum only range
    Range r2=new Range(Integer.valueOf(1),null);
    assertEquals(r2.getMin(),Integer.valueOf(1));
    // Maximum only range
    Range r3=new Range(null,Integer.valueOf(1));
    assertEquals(r3.getMin(),null);
    // Standard range
    Range r4=new Range(1,2);
    assertEquals(r4.getMin(),Integer.valueOf(1));
  }

  /**
   * Test method for {@link delta.common.utils.math.Range#getMax()}.
   */
  @Test
  void testGetMax()
  {
    // Empty range
    Range r1=new Range();
    assertEquals(r1.getMax(),null);
    // Minimum only range
    Range r2=new Range(Integer.valueOf(1),null);
    assertEquals(r2.getMax(),null);
    // Maximum only range
    Range r3=new Range(null,Integer.valueOf(1));
    assertEquals(r3.getMax(),Integer.valueOf(1));
    // Standard range
    Range r4=new Range(1,2);
    assertEquals(r4.getMax(),Integer.valueOf(2));
  }

  /**
   * Test method for {@link delta.common.utils.math.Range#contains(int)}.
   */
  @Test
  void testContains()
  {
    // Empty range
    Range r1=new Range();
    assertTrue(r1.contains(1));
    // Minimum only range
    Range r2=new Range(Integer.valueOf(1),null);
    assertTrue(r2.contains(1));
    assertTrue(r2.contains(2));
    assertFalse(r2.contains(0));
    // Maximum only range
    Range r3=new Range(null,Integer.valueOf(1));
    assertTrue(r3.contains(1));
    assertTrue(r3.contains(0));
    assertFalse(r3.contains(2));
    // Standard range
    Range r4=new Range(1,2);
    assertTrue(r4.contains(1));
    assertTrue(r4.contains(2));
    assertFalse(r4.contains(0));
    assertFalse(r4.contains(3));
  }

  /**
   * Test method for {@link delta.common.utils.math.Range#equals(java.lang.Object)}.
   */
  @Test
  void testEqualsObject()
  {
    // Empty range
    Range r1=new Range();
    assertEquals(r1,new Range());
    assertFalse(r1.equals(new Range(null,Integer.valueOf(0))));
    assertFalse(r1.equals(new Range(Integer.valueOf(0),null)));
    assertFalse(r1.equals(new Range(1,2)));
    // Minimum only range
    Range r2=new Range(Integer.valueOf(1),null);
    assertEquals(r2,new Range(Integer.valueOf(1),null));
    assertFalse(r2.equals(new Range(Integer.valueOf(0),null)));
    assertFalse(r2.equals(new Range(null,Integer.valueOf(1))));
    assertFalse(r2.equals(new Range()));
    assertFalse(r2.equals(new Range(1,2)));
    // Maximum only range
    Range r3=new Range(null,Integer.valueOf(1));
    assertEquals(r3,new Range(null,Integer.valueOf(1)));
    assertFalse(r3.equals(new Range(null,Integer.valueOf(0))));
    assertFalse(r3.equals(new Range(Integer.valueOf(1),null)));
    assertFalse(r3.equals(new Range()));
    assertFalse(r3.equals(new Range(1,2)));
    // Standard range
    Range r4=new Range(1,2);
    assertEquals(r4,new Range(1,2));
    assertFalse(r4.equals(new Range(null,Integer.valueOf(2))));
    assertFalse(r4.equals(new Range(Integer.valueOf(1),null)));
    assertFalse(r4.equals(new Range()));
    assertFalse(r4.equals(new Range(2,1)));
    // Null object
    assertFalse(new Range().equals(null));
    // Other object
    assertFalse(new Range().equals(new Object()));
  }

  /**
   * Test method for {@link delta.common.utils.math.Range#hashCode()}.
   */
  @Test
  void testHashCode()
  {
    assertEquals(new Range().hashCode(),new Range().hashCode());
    assertEquals(new Range(1,2).hashCode(),new Range(1,2).hashCode());
    assertEquals(new Range(null,Integer.valueOf(2)).hashCode(),new Range(null,Integer.valueOf(2)).hashCode());
    assertEquals(new Range(Integer.valueOf(2),null).hashCode(),new Range(Integer.valueOf(2),null).hashCode());
  }

  /**
   * Test method for {@link delta.common.utils.math.Range#toString()}.
   */
  @Test
  void testToString()
  {
    assertEquals(new Range().toString(),":");
    assertEquals(new Range(1,2).toString(),"1:2");
    assertEquals(new Range(null,Integer.valueOf(2)).toString(),":2");
    assertEquals(new Range(Integer.valueOf(2),null).toString(),"2:");
  }
}
