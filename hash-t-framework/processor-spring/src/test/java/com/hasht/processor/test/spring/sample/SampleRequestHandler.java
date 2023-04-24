package com.hasht.processor.test.spring.sample;

import com.hasht.processor.core.RequestHandler;

public class SampleRequestHandler implements RequestHandler<SampleRequest> {

    public static final String DEFAULT_RESPONSE = "DONE";

    @Override
    public void handle(SampleRequest request) {
        request.setResponse(DEFAULT_RESPONSE);
    }

}
