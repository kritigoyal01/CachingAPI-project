package com.example.cachingapi.cache.ttl.strategy.impl;

import com.example.cachingapi.cache.CacheNode;
import com.example.cachingapi.cache.ttl.strategy.ExpirationStrategy;

public class ExpireAfterWrite implements ExpirationStrategy {
    private int timeToLiveInMinutes;


    public ExpireAfterWrite(int timeToLiveInMinutes) {
        this.timeToLiveInMinutes = timeToLiveInMinutes;
    }

    @Override
    public boolean isExpired(CacheNode cacheNode) {

        long currentTime = System.currentTimeMillis();
        long writeCacheNodeTime = cacheNode.getAdditionTime();

        long timeToLiveInMillis = timeToLiveInMinutes*60*1000;

        if (currentTime - writeCacheNodeTime >= timeToLiveInMillis) {
            return true;
        }
        return false;
    }
}
