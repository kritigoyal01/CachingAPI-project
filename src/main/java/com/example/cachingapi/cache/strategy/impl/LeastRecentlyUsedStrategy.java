package com.example.cachingapi.cache.strategy.impl;

import com.example.cachingapi.cache.CacheNode;
import com.example.cachingapi.cache.strategy.EvictionStrategy;
import com.example.cachingapi.cache.ttl.strategy.ExpirationStrategy;

import java.util.*;

public class LeastRecentlyUsedStrategy implements EvictionStrategy {

    private int maxCacheSize;
    private int currentCacheSize;
    private ExpirationStrategy expirationStrategy;
    Set<Integer> cache;
    Map<Integer, CacheNode> keyToCacheNodeMap;



    public LeastRecentlyUsedStrategy(int maxCacheSize, ExpirationStrategy expirationStrategy) {
        this.maxCacheSize = maxCacheSize;
        this.cache = new LinkedHashSet<>(maxCacheSize);
        this.currentCacheSize = 0;
        this.expirationStrategy = expirationStrategy;
        this.keyToCacheNodeMap = new HashMap<>();
    }


    //Check whether elemnet exists or not in the cache
    @Override
    public boolean doesElementExist(int element) {
        if (!cache.contains(element)) {
            return false;
        }
        CacheNode cacheNode = keyToCacheNodeMap.get(element);
        cache.remove(element);
        cache.add(element);
        cacheNode.setAccessTime(System.currentTimeMillis());
        keyToCacheNodeMap.put(element, cacheNode);
        return true;
    }

    //Add element in the cache after checking the cache size and time
    @Override
    public void addElement(int element) {
        if (expirationStrategy != null) {
            removeExpiredElements();
        }
        if (cache.size() == maxCacheSize) {
            int firstElement = cache.iterator().next();
            cache.remove(firstElement);
            keyToCacheNodeMap.remove(firstElement);
        }
        cache.add(element);
        keyToCacheNodeMap.put(element, new CacheNode(System.currentTimeMillis(), System.currentTimeMillis(), element));
    }

    public void removeExpiredElements(){
        Iterator cacheIterator = cache.iterator();
        while (cacheIterator.hasNext()) {
            int cacheElement = (int) cacheIterator.next();
            if (expirationStrategy.isExpired(keyToCacheNodeMap.get(cacheElement))) {
                cache.remove(cacheElement);
                keyToCacheNodeMap.remove(cacheElement);
            }
        }
    }
}
