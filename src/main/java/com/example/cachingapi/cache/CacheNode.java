package com.example.cachingapi.cache;

public class CacheNode {

    long accessTime;
    long additionTime;
    int element;

    public CacheNode(long accessTime, long additionTime,int element){
        this.additionTime =additionTime;
        this.accessTime = accessTime;
        this.element = element;

    }
    public long getAdditionTime(){

        return this.additionTime;
    }
    public void setAccessTime(long accessTime) {
        this.accessTime = accessTime;
    }


}
