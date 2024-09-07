package delta.common.utils.io.streams;

import org.junit.jupiter.api.Test;

/**
 * Simple test class for the IndentableStream.
 * @author DAM
 */
public class IndentableStreamTest
{
  /**
   * Some tests.
   */
  @Test
  void test()
  {
    IndentableStream s=new IndentableStream(System.out);
    System.out.println("****");
    s.println("Coucou");
    System.out.println("****");
    s.setIndentationLevel(1);
    s.println("Coucou");
    System.out.println("****");
    s.println("\nCoucou");
    System.out.println("****");
    s.println("\r\nCoucou\nTiti");
  }
}
