package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever {
  private OriginalRetriever originalRetriever;
  private LRUCache lruCache = new LRUCache(10);
  
  public CachingProxyRetriever(Storage storage) {
    this.originalRetriever = new OriginalRetriever(storage);
  }
  
  @Override
  public Object retrieve(long id) {
    Object cacheObj = lruCache.find(id);
    if (cacheObj == null) {
      Object obj = originalRetriever.retrieve(id);
      lruCache.set(id, obj);
      return obj;
    } else {
      return cacheObj;
    }
  }
}
