package com.hasht.processor.spring;

import org.springframework.beans.BeansException;

public class NoHandlerFoundException extends BeansException {

    public NoHandlerFoundException(Class<?> request) {
        super("No handler found for request: " + request.getName());
    }

}
