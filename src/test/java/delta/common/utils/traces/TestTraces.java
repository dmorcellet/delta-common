package delta.common.utils.traces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Traces test.
 * @author DAM
 */
@DisplayName("Traces test")
class TestTraces
{
  private static final Logger LOGGER=LoggerFactory.getLogger(TestTraces.class);

  /**
   * Performance test.
   */
  @Test
  void testLoggersPerformance()
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
