package com.example.cachingapi;

import com.example.cachingapi.cache.CacheNode;
import com.example.cachingapi.cache.ttl.strategy.ExpirationStrategy;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ExpirationTest {
    private ExpirationStrategy expirationStrategy;
    private CacheNode cacheNode;

    //Check whether time doesnot expires
    @Test
    public void isExpiredNotExpired() throws Exception {
        cacheNode = new CacheNode(10,10,1);
        assertThat(expirationStrategy.isExpired(cacheNode)).isEqualTo(true);
    }

    //Check whether time expires
    @Test
    public void checkisExpired() throws Exception {
        cacheNode = new CacheNode(10,11,2);
        assertThat(expirationStrategy.isExpired(cacheNode)).isEqualTo(false);
    }
}
