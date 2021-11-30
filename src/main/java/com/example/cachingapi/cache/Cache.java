package com.example.cachingapi.cache;
import com.example.cachingapi.cache.strategy.EvictionStrategy;

public class Cache {

    private final String clientId;
    int cacheId;
    public EvictionStrategy getEvictionStrategy() {
        return evictionStrategy;
    }

    EvictionStrategy evictionStrategy;

    public Cache(EvictionStrategy evictionStrategy, int cacheId, String clientId) {
        this.evictionStrategy = evictionStrategy;
        this.cacheId = cacheId;
        this.clientId = clientId;

    }


}
