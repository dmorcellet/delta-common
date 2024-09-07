package delta.common.utils.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manages sleep time.
 * @author DAM
 */
public class SleepManager
{
  private static final Logger LOGGER=LoggerFactory.getLogger(SleepManager.class);

  /**
   * Make this thread sleep for a specified amount of time. 
   * @param milliseconds Time to sleep.
   */
  public static void sleep(long milliseconds)
  {
    try
    {
      Thread.sleep(milliseconds);
    }
    catch(InterruptedException ie)
    {
      LOGGER.error(ie);
    }
  }
}
