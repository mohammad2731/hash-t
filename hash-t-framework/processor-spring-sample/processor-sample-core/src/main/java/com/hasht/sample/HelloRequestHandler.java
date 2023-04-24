package com.hasht.sample;

import com.hasht.processor.core.Request;
import com.hasht.processor.core.RequestHandler;

public class HelloRequestHandler implements RequestHandler<HelloRequestHandler.HelloRequest> {

    public static final String DEFAULT_RESPONSE = "Hello, this is Handler!";

    @Override
    public void handle(HelloRequest request) {
        request.setResponse(DEFAULT_RESPONSE);
    }

    public static class HelloRequest extends Request<String> {
    }

}
