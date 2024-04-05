package delta.common.utils.io;

/**
 * Simple facade for the console.
 * @author DAM
 */
public class Console
{
  /**
   * Print an object on a dedicated line.
   * @param o Object to use.
   */
  public static void println(Object o)
  {
    System.out.println(o); // NOSONAR
  }

  /**
   * Print an object on a dedicated line, with indentation.
   * @param o Object to use.
   * @param level Indentation level.
   */
  public static void println(Object o, int level)
  {
    for(int i=0;i<level;i++)
    {
      System.out.print("\t"); // NOSONAR
    }
    System.out.println(o); // NOSONAR
  }
}
