package com.example.cachingapi.controller;

import com.example.cachingapi.cache.CacheNode;
import com.example.cachingapi.cache.strategy.impl.LeastRecentlyUsedStrategy;
import com.example.cachingapi.cache.ttl.strategy.ExpirationStrategy;
import com.example.cachingapi.cache.ttl.strategy.impl.ExpireAfterWrite;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
    public class CachingController {

        LeastRecentlyUsedStrategy leastRecentlyUsedStrategy;
        ExpirationStrategy expirationStrategy;
        @GetMapping("/{strategyId}")
        public ResponseEntity<> createCache (@PathVariable String strategyId)
        {
            if(strategyId == "LRU"){
                //leastRecentlyUsedStrategy.addElement();
                expirationStrategy = new ExpireAfterWrite(10);
                leastRecentlyUsedStrategy = new LeastRecentlyUsedStrategy(5,expirationStrategy);
            }
            System.out.println("Done");
            return ResponseEntity.ok(cacheID);
        }

        @GetMapping("/{element}/{cacheID}")
        public ResponseEntity<> addElement(@PathVariable int element, int cacheId){
            leastRecentlyUsedStrategy.addElement(element);
            return ResponseEntity.ok(200);
        }

    }
