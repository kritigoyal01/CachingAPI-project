package com.example.cachingapi.cache.Notify;

import com.example.cachingapi.cache.CacheNode;

import java.util.List;

public class Notification {
    public void sendNotification(List<Client> clients) {
        for (Client c : clients) {
            System.out.println("Eviction Happened");
        }
    }
}
