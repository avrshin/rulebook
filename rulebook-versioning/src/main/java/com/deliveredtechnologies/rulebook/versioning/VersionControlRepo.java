package com.deliveredtechnologies.rulebook.versioning;

import java.io.File;
import java.net.URL;

/**
 * Created by clong on 3/10/17.
 */
public interface VersionControlRepo {
  URL getLocalUrlForClass(String name);
}
