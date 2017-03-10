package com.deliveredtechnologies.rulebook.versioning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by clong on 3/10/17.
 */
public class RuleBookClassLoader extends ClassLoader {

  private static Logger LOGGER = LoggerFactory.getLogger(RuleBookClassLoader.class);

  private VersionControlRepo _repo;

  public RuleBookClassLoader(ClassLoader parent, VersionControlRepo repo) {
    super(parent);
    _repo = repo;
  }

  public RuleBookClassLoader(ClassLoader parent) {
    super(parent);
  }

  public void setVersionControlRepo(VersionControlRepo repo) {
    _repo = repo;
  }

  @Override
  public Class<?> loadClass(String name) throws ClassNotFoundException {
    try {
      URL classUrl = _repo.getLocalUrlForClass(name);
      URLConnection connection = classUrl.openConnection();
      InputStream istream = connection.getInputStream();
      byte[] bytes = new byte[256];
      ByteArrayOutputStream ostream = new ByteArrayOutputStream();
      int size = istream.read(bytes);

      while (size > 0) {
        ostream.write(bytes, 0, size);
        size = istream.read(bytes);
      }

      istream.close();
      byte[] data = ostream.toByteArray();
      return defineClass(name, data, 0, data.length);
    } catch (IOException ex) {
      LOGGER.error("Unable to load class '" + name + "'", ex);
    }

    return null; //if we can't load the class
  }
}
