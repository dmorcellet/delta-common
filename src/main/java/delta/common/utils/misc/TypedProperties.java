package delta.common.utils.misc;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import delta.common.utils.BooleanTools;
import delta.common.utils.NumericTools;
import delta.common.utils.io.StreamTools;

/**
 * Typed properties access.
 * @author DAM
 */
public class TypedProperties
{
  private static final Logger LOGGER=LoggerFactory.getLogger(TypedProperties.class);

  private Properties _props;

  /**
   * Constructor.
   */
  public TypedProperties()
  {
    _props=new Properties();
  }

  /**
   * Load properties files.
   * @param inFile Input file.
   * @return <code>true</code> if it was successfull, <code>false</code> otherwise.
   */
  public boolean loadFromFile(File inFile)
  {
    boolean ret=false;
    FileInputStream fis=null;
    try
    {
      fis=new FileInputStream(inFile);
      ret=loadFromInputStream(fis);
    }
    catch(IOException ioe)
    {
      LOGGER.error("Cannot load properties from file ["+inFile+"]!",ioe);
    }
    return ret;
  }

  /**
   * Load properties from an input stream.
   * @param is Input stream.
   * @return <code>true</code> if it was successfull, <code>false</code> otherwise.
   */
  public boolean loadFromInputStream(InputStream is)
  {
    boolean ret;
    Properties props=new Properties();
    try
    {
      props.load(is);
      for(Map.Entry<Object,Object> entry : props.entrySet())
      {
        _props.put(entry.getKey(),entry.getValue());
      }
      ret=true;
    }
    catch(IOException ioe)
    {
      ret=false;
      LOGGER.error("Cannot load properties!",ioe);
    }
    finally
    {
      StreamTools.close(is);
    }
    return ret;
  }

  /**
   * Save properties files.
   * @param outFile Output file.
   * @return <code>true</code> if it was successfull, <code>false</code> otherwise.
   */
  public boolean saveToFile(File outFile)
  {
    boolean ret;
    FileOutputStream fos=null;
    try
    {
      fos=new FileOutputStream(outFile);
      _props.store(fos,"");
      ret=true;
    }
    catch(IOException ioe)
    {
      ret=false;
      LOGGER.error("Cannot save properties to file ["+outFile+"]!",ioe);
    }
    finally
    {
      StreamTools.close(fos);
    }
    return ret;
  }

  /**
   * Get all keys.
   * @return a set of keys.
   */
  public Set<String> getAllKeys()
  {
    Set<String> ret=new HashSet<String>();
    for(Object key : _props.keySet())
    {
      ret.add((String)key);
    }
    return ret;
  }

  /**
   * Indicates if this properties set has the given property.
   * @param name Name of the property to get.
   * @return <code>true</code> if it does, <code>false</code> otherwise.
   */
  public boolean hasProperty(String name)
  {
    return _props.containsKey(name);
  }

  /**
   * Get a typed property value.
   * @param name Property name.
   * @param valueClass Type of the desired value.
   * @return A value or <code>null</code> if not found.
   */
  public <T> T getProperty(String name, Class<T> valueClass)
  {
    T ret=null;
    Object value=_props.get(name);
    if (value!=null)
    {
      if (valueClass.isAssignableFrom(value.getClass()))
      {
        ret=valueClass.cast(value);
      }
    }
    return ret;
  }

  /**
   * Set a typed property value.
   * @param name Property name.
   * @param value Value to set.
   */
  public <T> void setProperty(String name, T value)
  {
    if (value==null)
    {
      _props.remove(name);
    }
    else
    {
      _props.put(name,value);
    }
  }

  /**
   * Add some properties from another properties set.
   * @param properties Properties to add.
   */
  public void addProperties(TypedProperties properties)
  {
    _props.putAll(properties._props);
  }

  /**
   * Get the value of an integer property.
   * @param name Property name.
   * @param defaultValue Default value, used if the property does not exist.
   * @return An integer value.
   */
  public int getIntProperty(String name, int defaultValue)
  {
    int ret=defaultValue;
    String value=_props.getProperty(name,null);
    if (value!=null)
    {
      ret=NumericTools.parseInt(value,ret);
    }
    return ret;
  }

  /**
   * Set the value of an integer property.
   * @param name Property name.
   * @param value Value to set.
   */
  public void setIntProperty(String name, int value)
  {
    _props.setProperty(name,String.valueOf(value));
  }

  /**
   * Get the value of an integer property.
   * @param name Property name.
   * @return An integer value or <code>null</code> if not found or not an integer.
   */
  public Integer getIntegerProperty(String name)
  {
    Integer ret=null;
    String value=_props.getProperty(name,null);
    if (value!=null)
    {
      ret=NumericTools.parseInteger(value);
    }
    return ret;
  }

  /**
   * Get the value of a long property.
   * @param name Property name.
   * @param defaultValue Default value, used if the property does not exist.
   * @return An integer value.
   */
  public long getLongProperty(String name, long defaultValue)
  {
    long ret=defaultValue;
    String value=_props.getProperty(name,null);
    if (value!=null)
    {
      ret=NumericTools.parseLong(value,ret);
    }
    return ret;
  }

  /**
   * Set the value of a long property.
   * @param name Property name.
   * @param value Value to set.
   */
  public void setLongProperty(String name, long value)
  {
    _props.setProperty(name,String.valueOf(value));
  }

  /**
   * Get the value of a strings list.
   * @param name Property name.
   * @return A possibly empty list of strings or <code>null</code> if not found.
   */
  public List<String> getStringList(String name)
  {
    List<String> ret=null;
    String countKey=name+".count";
    String countStr=_props.getProperty(countKey);
    if (countStr!=null)
    {
      int nb=NumericTools.parseInt(countStr,-1);
      if (nb>0)
      {
        ret=new ArrayList<String>();
        for(int i=0;i<nb;i++)
        {
          String key=name+"."+(i+1);
          String value=_props.getProperty(key);
          ret.add(value);
        }
      }
    }
    return ret;
  }

  /**
   * Set the value of a strings list property.
   * @param name Property name.
   * @param values Values to set.
   */
  public void setStringList(String name, List<String> values)
  {
    int nbValues=values.size();
    String countKey=name+".count";
    _props.setProperty(countKey,String.valueOf(nbValues));
    for(int i=0;i<nbValues;i++)
    {
      String key=name+"."+(i+1);
      _props.setProperty(key,values.get(i));
    }
  }

  /**
   * Get the value of a string property.
   * @param name Property name.
   * @param defaultValue Default value, used if the property does not exist.
   * @return A string value or the default value.
   */
  public String getStringProperty(String name, String defaultValue)
  {
    String ret=_props.getProperty(name,defaultValue);
    return ret;
  }

  /**
   * Set the value of a string property.
   * @param name Property name.
   * @param value Value to set.
   */
  public void setStringProperty(String name, String value)
  {
    _props.put(name,value);
  }

  /**
   * Get the value of a boolean property.
   * @param name Property name.
   * @param defaultValue Default value, used if the property does not exist.
   * @return A boolean value or the default value.
   */
  public boolean getBooleanProperty(String name, boolean defaultValue)
  {
    String booleanStr=_props.getProperty(name,null);
    return BooleanTools.parseBoolean(booleanStr,defaultValue);
  }

  /**
   * Set the value of a boolean property.
   * @param name Property name.
   * @param value Value to set.
   */
  public void setBooleanProperty(String name, boolean value)
  {
    _props.put(name,Boolean.toString(value));
  }

  /**
   * Get the value of a bounds property.
   * @param name Property name.
   * @return A bounds value or <code>null</code> if not found.
   */
  public Rectangle getBoundsProperty(String name)
  {
    Rectangle r=null;
    String propValue=_props.getProperty(name);
    if (propValue!=null)
    {
      String[] items=propValue.split(",");
      if ((items!=null) && (items.length==4))
      {
        Integer x=NumericTools.parseInteger(items[0]);
        Integer y=NumericTools.parseInteger(items[1]);
        Integer width=NumericTools.parseInteger(items[2]);
        Integer height=NumericTools.parseInteger(items[3]);
        if ((x!=null) && (y!=null) && (width!=null) && (height!=null))
        {
          r=new Rectangle(x.intValue(),y.intValue(),width.intValue(),height.intValue());
        }
      }
      if (r==null)
      {
        LOGGER.error("Wrong rectangle format ["+propValue+"]");
      }
    }
    return r;
  }

  /**
   * Set the value of a bounds property.
   * @param name Property name.
   * @param r Value to set.
   */
  public void setBoundsProperty(String name, Rectangle r)
  {
    String value=r.x+","+r.y+","+r.width+","+r.height;
    _props.setProperty(name,value);
  }

  /**
   * Remove property.
   * @param name Property name.
   */
  public void removeProperty(String name)
  {
    _props.remove(name);
  }


  /**
   * Remove property.
   * @param name Property name.
   */
  public void removeListProperty(String name)
  {
    String countKey=name+".count";
    Object countStr=_props.remove(countKey);
    if (countStr!=null)
    {
      int nb=NumericTools.parseInt((String)countStr,0);
      for(int i=0;i<nb;i++)
      {
        String key=name+"."+(i+1);
        _props.remove(key);
      }
    }
  }
}
