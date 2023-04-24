package com.hasht.processor.test.spring;

import com.hasht.processor.core.DouplicatedHandlerException;
import com.hasht.processor.spring.NoHandlerFoundException;
import com.hasht.processor.spring.SpringRequestProcessor;
import com.hasht.processor.test.infra.HashTIntegrationTest;
import com.hasht.processor.test.spring.sample.AnotherSampleRequest;
import com.hasht.processor.test.spring.sample.AnotherSampleRequestHandler;
import com.hasht.processor.test.spring.sample.RequestWithDuplicatedHandlers;
import com.hasht.processor.test.spring.sample.RequestWithoutHandler;
import com.hasht.processor.test.spring.sample.SampleRequest;
import com.hasht.processor.test.spring.sample.SampleRequestHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@HashTIntegrationTest
class SpringRequestProcessorIT {

    @Autowired
    SpringRequestProcessor processor;

    @Test
    void processor_shouldProcess_request() {
        final var request = new SampleRequest();

        processor.process(request);

        assertThat(request.getResponse()).isEqualTo(SampleRequestHandler.DEFAULT_RESPONSE);
    }

    @Test
    void processor_whenThereIsNoHandler_shouldThrowNoHandlerFoundException() {
        final var request = new RequestWithoutHandler();

        assertThrows(NoHandlerFoundException.class, () -> processor.process(request));
    }

    @Test
    void processor_shouldProcessBySuitableHandler() {
        final var request = new SampleRequest();
        final var anotherRequest = new AnotherSampleRequest();

        processor.process(request);
        processor.process(anotherRequest);

        assertThat(request.getResponse()).isEqualTo(SampleRequestHandler.DEFAULT_RESPONSE);
        assertThat(anotherRequest.getResponse()).isEqualTo(AnotherSampleRequestHandler.DEFAULT_RESPONSE);
    }

    @Test
    void processor_whenMultipleHandlerFound_shouldThrowDuplicatedHandlerException() {
        final var request = new RequestWithDuplicatedHandlers();

        assertThrows(DouplicatedHandlerException.class, () -> processor.process(request));

    }

    @Test
    void processor_shouldNotHandleByHandlerOfSuperClass() {
        final var request = new SampleRequest() {};

        assertThrows(NoHandlerFoundException.class, () -> processor.process(request));

    }

}
