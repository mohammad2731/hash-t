package com.ht.cqrs.core;

public interface RequestHandler<R extends Request<?>> {

    void handle(R request);

}
