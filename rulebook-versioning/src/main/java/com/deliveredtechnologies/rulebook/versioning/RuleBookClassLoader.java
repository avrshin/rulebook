package com.deliveredtechnologies.rulebook.versioning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;

/**
 * Created by clong on 3/10/17.
 */
public class RuleBookClassLoader extends URLClassLoader {

  private static Logger LOGGER = LoggerFactory.getLogger(RuleBookClassLoader.class);

  public RuleBookClassLoader(URL[] urls) {
    super(urls, null);
  }
}
