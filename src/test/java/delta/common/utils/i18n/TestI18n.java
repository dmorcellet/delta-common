package delta.common.utils.i18n;

import org.junit.jupiter.api.DisplayName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Internationalization test.
 * @author DAM
 */
@DisplayName("I18N test")
public class TestI18n
{
  private static final Logger LOGGER=LoggerFactory.getLogger(TestI18n.class);

  private static final Translator _translator=TranslatorsManager.getInstance().createTranslator(TestI18n.class);

  /**
   * Test basic i18n usage.
   */
  public void testTranslation()
  {
    String simpleMsg=_translator.translate("test");
    LOGGER.info("Translation for 'test' : '"+simpleMsg+"'");
    Object[] params=new String[] {"1", "2", "3"};
    String complexMsg=_translator.translate("test_args",params);
    LOGGER.info("Translation for 'test_args' : '"+complexMsg+"'");
  }
}
