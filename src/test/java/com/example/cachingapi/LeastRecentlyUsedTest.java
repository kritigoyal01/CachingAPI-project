package com.example.cachingapi;

import com.example.cachingapi.cache.CacheNode;
import com.example.cachingapi.cache.strategy.EvictionStrategy;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LeastRecentlyUsedTest {

    private EvictionStrategy evictionStrategy = null;
    private CacheNode cacheNode= null;

    @Test
    public void elementExists() throws Exception {
        cacheNode = new CacheNode(10,10,1);
        assertThat(evictionStrategy.doesElementExist(1)).isEqualTo(true);
    }

    @Test
    public void elementDoesnotExists() throws Exception {
        cacheNode = new CacheNode(10,10,1);
        assertThat(evictionStrategy.doesElementExist(1)).isEqualTo(false);
    }
}
