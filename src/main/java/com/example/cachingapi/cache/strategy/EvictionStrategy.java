package com.example.cachingapi.cache.strategy;

import com.example.cachingapi.cache.CacheNode;

import java.util.Map;

public interface EvictionStrategy {
    void addElement(int element);
    boolean doesElementExist(int element);
}
