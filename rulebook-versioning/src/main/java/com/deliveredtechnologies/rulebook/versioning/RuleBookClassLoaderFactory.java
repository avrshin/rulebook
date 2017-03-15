package com.deliveredtechnologies.rulebook.versioning;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * RuleBookClassLoaderFactory creates one ClassLoader per artifact
 */
public class RuleBookClassLoaderFactory implements ClassLoaderFactory {

  private Map<String, ClassLoader> artifactClassLoaderMap = new HashMap<>();
  private VersionControlRepo repo;
  private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
  private final Lock readLock = readWriteLock.readLock();
  private final Lock writeLock = readWriteLock.writeLock();

  @Override
  public ClassLoader getClassLoaderByArtifact(String artifactName) {
    readLock.lock();
    ClassLoader loader = artifactClassLoaderMap.get(artifactName);
    if (loader != null) {
      return loader;
    }
    readLock.unlock();

    writeLock.lock();
    loader = artifactClassLoaderMap.get(artifactName);
    if (loader != null) {
      return loader;
    }
    loader = new RuleBookClassLoader(repo.getLocalUrlForArtifact(artifactName));
    artifactClassLoaderMap.put(artifactName, loader);
    writeLock.unlock();

    return loader;
  }
}
