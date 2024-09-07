package delta.common.utils.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test for class {@link RangeComparator}.
 * @author DAM
 */
class RangeComparatorTest
{
  /**
   * Test method for {@link delta.common.utils.math.RangeComparator#compare(delta.common.utils.math.Range, delta.common.utils.math.Range)}.
   */
  @Test
  void testCompare()
  {
    RangeComparator c=new RangeComparator();
    // Empty range
    Range r1=new Range();
    assertEquals(c.compare(r1,new Range()),0);
    assertEquals(c.compare(r1,new Range(null,Integer.valueOf(0))),-1);
    assertEquals(c.compare(r1,new Range(Integer.valueOf(0),null)),-1);
    assertEquals(c.compare(r1,new Range(1,2)),-1);
    // Minimum only range
    Range r2=new Range(Integer.valueOf(1),null);
    assertEquals(c.compare(r2,new Range(Integer.valueOf(1),null)),0);
    assertEquals(c.compare(r2,new Range(Integer.valueOf(0),null)),1);
    assertEquals(c.compare(r2,new Range(null,Integer.valueOf(1))),1);
    assertEquals(c.compare(r2,new Range()),1);
    assertEquals(c.compare(r2,new Range(1,2)),-1);
    // Maximum only range
    Range r3=new Range(null,Integer.valueOf(1));
    assertEquals(c.compare(r3,new Range(null,Integer.valueOf(1))),0);
    assertEquals(c.compare(r3,new Range(null,Integer.valueOf(0))),1);
    assertEquals(c.compare(r3,new Range(Integer.valueOf(1),null)),-1);
    assertEquals(c.compare(r3,new Range()),1);
    assertEquals(c.compare(r3,new Range(1,2)),-1);
    // Standard range
    Range r4=new Range(1,2);
    assertEquals(c.compare(r4,new Range(1,2)),0);
    assertEquals(c.compare(r4,new Range(null,Integer.valueOf(2))),1);
    assertEquals(c.compare(r4,new Range(Integer.valueOf(1),null)),1);
    assertEquals(c.compare(r4,new Range()),1);
    assertEquals(c.compare(r4,new Range(2,1)),-1);
  }
}
