package com.deliveredtechnologies.rulebook.versioning;

import org.junit.Test;

import java.net.URL;

/**
 * Created by clong on 3/10/17.
 */
public class TestRuleBookClassLoader {
  @Test
  public void classLoaderCanLoadSameClass() throws Exception {
    URL[] url = {new URL("http://localhost:8080/artifactory/libs-release/com/deliveredtechnologies/rulebook-core/0.3.2/rulebook-core-0.3.2.jar")};
    RuleBookClassLoader classLoader = new RuleBookClassLoader(url);
  }
}
