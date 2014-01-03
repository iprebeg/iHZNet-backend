package com.prebeg.ihznet.service;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Collections;

import org.springframework.stereotype.Component;

import com.prebeg.ihznet.model.Raspored;

@Component
public class CachedRasporedService
{
  public class LRUCache<A,B> extends LinkedHashMap<A,B>
  {
    private final int maxEntries;

    public LRUCache(final int maxEntries)
    {
      super(maxEntries + 1, 1.0f, true);
      this.maxEntries = maxEntries;
    }

    @Override
    protected boolean removeEldestEntry(final Map.Entry<A,B> eldest)
    {
      return super.size() > maxEntries;
    }
  }

  private static int CACHE_SIZE = 200;

  Map<String,Raspored> cache = Collections.synchronizedMap(new LRUCache<String,Raspored>(CACHE_SIZE));

  public Raspored getRaspored(String key)
  {
    return cache.get(key);
  }

  public void putRaspored(String key, Raspored raspored)
  {
    cache.put(key,raspored);
  }
}
