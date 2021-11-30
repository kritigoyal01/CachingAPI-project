package com.example.cachingapi.cache.exception;

public class InvalidOperationException extends Exception{
    public String message;

    public InvalidOperationException(String message) {
        this.message = message;
    }
}
