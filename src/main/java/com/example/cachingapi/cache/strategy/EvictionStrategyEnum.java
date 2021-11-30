package com.example.cachingapi.cache.strategy;

public enum EvictionStrategyEnum {
    LRU(0);

    private int strategyId;

    EvictionStrategyEnum(int strategyId) {
        this.strategyId = strategyId;
    }

    public static EvictionStrategyEnum getByStrategyId(int strategyId) {
        for (EvictionStrategyEnum evictionStrategy: values()) {
            if(evictionStrategy.strategyId == strategyId) {
                return evictionStrategy;
            }
        }
        return null;
    }
}


