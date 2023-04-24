package com.hasht.processor.core;

public class HandlerNotFoundException extends RuntimeException {

    public HandlerNotFoundException(Class<?> requestType) {
        super("There is no Request-handler for the request of type: " + requestType.getName());
    }

}
