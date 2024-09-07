package delta.common.utils.files.archives;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import delta.common.utils.io.FileIO;
import delta.common.utils.io.StreamTools;

/**
 * Builds archive files.
 * @author DAM
 */
public class ArchiveBuilder
{
  private static final Logger LOGGER=LoggerFactory.getLogger(ArchiveDeflater.class);

  private File _archiveFile;
  private FileOutputStream _fos;
  private BufferedOutputStream _bos;
  private ZipOutputStream _jos;

  /**
   * Constructor.
   * @param archiveFile Archive file to write.
   */
  public ArchiveBuilder(File archiveFile)
  {
    _archiveFile=archiveFile;
  }

  /**
   * Initialize the build process.
   * @return <code>true</code> if it was sucessfull, <code>false</code> otherwise.
   */
  public boolean start()
  {
    boolean ok;
    try
    {
      _fos=new FileOutputStream(_archiveFile);
      _bos=new BufferedOutputStream(_fos);
      _jos=new ZipOutputStream(_bos);
      ok=true;
    }
    catch (IOException ioe)
    {
      LOGGER.error("",ioe);
      StreamTools.close(_jos);
      _jos=null;
      ok=false;
    }
    return ok;
  }

  /**
   * Add a file to this archive.
   * @param absolute File to add.
   * @param archivePath Entry path in the archive.
   * @return <code>true</code> if it was successfull, <code>false</code> otherwise.
   */
  public boolean addFile(File absolute, File archivePath)
  {
    boolean ok;
    try
    {
      ZipEntry entry=new ZipEntry(archivePath.getPath());
      entry.setSize(absolute.length());
      byte[] buffer=FileIO.readFile(absolute);
      _jos.putNextEntry(entry);
      _jos.write(buffer,0,buffer.length);
      _jos.closeEntry();
      ok=true;
    }
    catch (IOException ioe)
    {
      ok=false;
    }
    return ok;
  }

  /**
   * Add a file to this archive.
   * @param archivePath Entry path in the archive.
   * @return <code>true</code> if it was successfull, <code>false</code> otherwise.
   */
  private boolean addDirectoryEntry(File archivePath)
  {
    boolean ok;
    try
    {
      String path=archivePath.getPath();
      if (!path.endsWith("/")) path=path+"/";
      ZipEntry entry=new ZipEntry(path);
      _jos.putNextEntry(entry);
      _jos.closeEntry();
      ok=true;
    }
    catch (IOException ioe)
    {
      ok=false;
    }
    return ok;
  }

  /**
   * Add a directory to this archive (and recursively all of its files).
   * @param root Directory to add.
   * @param localRoot Entry path of the root dir in the archive.
   * @return <code>true</code> if it was successfull, <code>false</code> otherwise.
   */
  public boolean addDirectory(File root, File localRoot)
  {
    boolean ret=true;

    if (localRoot!=null)
    {
      addDirectoryEntry(localRoot);
    }
    File[] files=root.listFiles();
    File f;
    String name;
    File currentLocalFile;
    File currentGlobalFile;
    for(int i=0;i<files.length;i++)
    {
      f=files[i];
      name=f.getName();
      currentGlobalFile=new File(root,name);
      currentLocalFile=new File(localRoot,name);
      if (f.isDirectory())
      {
        ret=addDirectory(currentGlobalFile,currentLocalFile);
      }
      else
      {
        ret=addFile(currentGlobalFile,currentLocalFile);
      }
      if (!ret)
      {
        break;
      }
    }
    return ret;
  }

  /**
   * Finish the build process.
   */
  public void terminate()
  {
    StreamTools.close(_jos);
    _jos=null;
    StreamTools.close(_bos);
    _bos=null;
    StreamTools.close(_fos);
    _fos=null;
  }
}
