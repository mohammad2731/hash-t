package com.hasht.processor.test.spring.sample;

import com.hasht.processor.core.RequestHandler;

public class AnotherSampleRequestHandler implements RequestHandler<AnotherSampleRequest> {

    public static final String DEFAULT_RESPONSE = "ANOTHER_DONE";

    @Override
    public void handle(AnotherSampleRequest request) {
        request.setResponse(DEFAULT_RESPONSE);
    }

}
