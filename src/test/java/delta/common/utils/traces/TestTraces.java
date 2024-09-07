package delta.common.utils.traces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

/**
 * Traces test.
 * @author DAM
 */
public class TestTraces extends TestCase
{
  private static final Logger LOGGER=LoggerFactory.getLogger(TestTraces.class);

  /**
   * Constructor.
   */
  public TestTraces()
  {
    super("Traces test");
  }

  /**
   * Performance test.
   */
  public void testLoggersPerformance()
  {
    final int NB=100000;
    final int GROUP=1000;
    long now1=System.currentTimeMillis();
    for(int i=0;i<NB;i++)
    {
      LOGGER.info("titi");
    }
    long now2=System.currentTimeMillis();
    long delta=now2-now1;
    System.out.println("Took "+delta+"ms ("+((GROUP*delta)/NB)+"ms/("+GROUP+" traces)");
  }
}
