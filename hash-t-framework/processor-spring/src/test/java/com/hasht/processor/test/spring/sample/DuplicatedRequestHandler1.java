package com.hasht.processor.test.spring.sample;

import com.hasht.processor.core.RequestHandler;

public class DuplicatedRequestHandler1 implements RequestHandler<RequestWithDuplicatedHandlers> {

    @Override
    public void handle(RequestWithDuplicatedHandlers request) {
    }

}
