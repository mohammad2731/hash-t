package com.hasht.processor.core;

public interface RequestHandler<R extends Request<?>> {

    void handle(R request);

}
