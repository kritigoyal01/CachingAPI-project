package com.example.cachingapi.cache.ttl.strategy;

import com.example.cachingapi.cache.CacheNode;

public interface ExpirationStrategy {
    boolean isExpired(CacheNode cacheNode);
}
