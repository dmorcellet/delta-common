package delta.common.utils.i18n;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import delta.common.utils.i18n.io.xml.LabelsXMLParser;

/**
 * Facade for localization labels.
 * @author DAM
 */
public class LabelsFacade
{
  private static final Logger LOGGER=LoggerFactory.getLogger(LabelsFacade.class);

  private File _rootDir;

  /**
   * Constructor.
   * @param rootDir Root directory for labels files.
   */
  public LabelsFacade(File rootDir)
  {
    _rootDir=rootDir;
  }

  /**
   * Get a labels manager.
   * @param key Identifying key for the labels manager.
   * @param localeKey Locale key.
   * @return A labels manager.
   */
  public SingleLocaleLabelsManager getLabelsMgr(String key, String localeKey)
  {
    LOGGER.debug("Loading labels file: "+key);
    SingleLocaleLabelsManager mgr=loadLabelsFile(key,localeKey);
    if (mgr==null)
    {
      // If no labels file, use the English file
      mgr=loadLabelsFile(key,"en");
    }
    if (mgr==null)
    {
      mgr=new SingleLocaleLabelsManager(localeKey);
    }
    return mgr;
  }

  private SingleLocaleLabelsManager loadLabelsFile(String key, String localeKey)
  {
    File labelsDir=new File(_rootDir,localeKey);
    String filename=key+".xml";
    File from=new File(labelsDir,filename);
    SingleLocaleLabelsManager mgr=null;
    if (from.exists())
    {
      long now=System.currentTimeMillis();
      mgr=new LabelsXMLParser().parseSingleLocaleLabels(from);
      long now2=System.currentTimeMillis();
      LOGGER.debug("Took: "+(now2-now)+"ms");
    }
    return mgr;
  }
}
