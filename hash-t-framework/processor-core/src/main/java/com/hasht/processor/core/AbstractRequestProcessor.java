package com.hasht.processor.core;

public abstract class AbstractRequestProcessor {

    public <T> void process(Request<T> request) {
        final RequestHandler<Request<T>> requestHandler = getHandler(request);
        requestHandler.handle(request);
    }

    protected abstract <T, R extends Request<T>> RequestHandler<R> getHandler(R request);

}
