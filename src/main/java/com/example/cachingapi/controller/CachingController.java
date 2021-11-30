package com.example.cachingapi.controller;

import com.example.cachingapi.cache.Cache;
import com.example.cachingapi.cache.Notify.Client;
import com.example.cachingapi.cache.strategy.EvictionStrategyEnum;
import com.example.cachingapi.cache.strategy.impl.LeastRecentlyUsedStrategy;
import com.example.cachingapi.cache.ttl.strategy.ExpirationStrategy;
import com.example.cachingapi.cache.ttl.strategy.impl.ExpireAfterWrite;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
    public class CachingController {

    Map<Integer, Cache> idToCacheMap;
    public CachingController(Map<Integer, Cache> idToCacheMap) {
        this.idToCacheMap = idToCacheMap;
    }

        @GetMapping("/{strategyId}/{clientId}")
        public ResponseEntity createCache (@PathVariable int strategyId, @PathVariable String clientId)
        {
            EvictionStrategyEnum evictionStrategyEnum = EvictionStrategyEnum.getByStrategyId(strategyId);
            if(evictionStrategyEnum == null) {
                return ResponseEntity.ok("Error");
            }
            int cacheId = (int)Math.floor(Math.random()*(101));
            if(evictionStrategyEnum == EvictionStrategyEnum.LRU){

                ExpirationStrategy expirationStrategy = new ExpireAfterWrite(10);
                LeastRecentlyUsedStrategy leastRecentlyUsedStrategy = new LeastRecentlyUsedStrategy(5,expirationStrategy);
                Cache cache = new Cache(leastRecentlyUsedStrategy, cacheId, clientId);
                idToCacheMap.put(cacheId, cache);

            }
            System.out.println("Done");
            return ResponseEntity.ok(cacheId);
        }

        @GetMapping("/{element}/{cacheID}")
        public ResponseEntity addElement(@PathVariable int element, int cacheId){
            if (!idToCacheMap.containsKey(cacheId)) {
                System.out.println("Cannot add element to cache");
            }
            Cache cache = idToCacheMap.get(cacheId);
            cache.getEvictionStrategy().addElement(element);
            return ResponseEntity.ok(cacheId);
        }

    }
