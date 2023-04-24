package com.hasht.processor.core;

import java.util.HashMap;
import java.util.Map;

public class DefaultRequestProcessor extends AbstractRequestProcessor {

    private final Map<Class<?>, RequestHandler<?>> handlerRegistry = new HashMap<>();

    public <T, R extends Request<T>> void setHandler(Class<R> requestType, RequestHandler<R> handler) {
        if (handlerRegistry.containsKey(requestType) && handlerRegistry.get(requestType) != handler) {
            throw new DouplicatedHandlerException(requestType);
        }
        this.handlerRegistry.put(requestType, handler);
    }

    @Override
    protected <T, R extends Request<T>> RequestHandler<R> getHandler(R request) {
        if (!handlerRegistry.containsKey(request.getClass())) {
            throw new HandlerNotFoundException(request.getClass());
        }
        return (RequestHandler<R>) handlerRegistry.get(request.getClass());
    }

}
