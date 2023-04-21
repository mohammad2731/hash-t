package com.ht.cqrs.core;

import org.assertj.core.api.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RequestProcessorTest {

    @Test
    void registerHandler_whenTheHandlerIsDuplicated_shouldThrowDouplicatedHandlerException() {
        final RequestProcessor requestProcessor = new RequestProcessor();
        requestProcessor.setHandler(SimpleRequest.class, new SimpleRequestHandler());
        final SimpleRequestHandler newHandler = new SimpleRequestHandler();

        assertThrows(
                DouplicatedHandlerException.class,
                () ->
                        requestProcessor.setHandler(SimpleRequest.class, newHandler)
        );
    }

    @Test
    void registerHandler_sameHanlerCouldBeRegisterMultipleTimes() {
        final RequestProcessor requestProcessor = new RequestProcessor();
        final SimpleRequestHandler handler = new SimpleRequestHandler();
        requestProcessor.setHandler(SimpleRequest.class, handler);

        assertDoesNotThrow(
                () ->
                        requestProcessor.setHandler(SimpleRequest.class, handler)
        );
    }

    @Test
    void process_shouldProcessRequest() {
        final SimpleRequest request = new SimpleRequest();
        final RequestProcessor requestProcessor = new RequestProcessor();
        requestProcessor.setHandler(SimpleRequest.class, new SimpleRequestHandler());

        requestProcessor.process(request);

        Assertions.assertThat(request.getResponse()).isNotNull();
    }

    @Test
    void process_whenHandlerNotFound_shouldThrowHandlerNotFoundException() {
        final SimpleRequest request = new SimpleRequest();
        final RequestProcessor requestProcessor = new RequestProcessor();

        assertThrows(HandlerNotFoundException.class, () -> requestProcessor.process(request));
    }

    static class SimpleRequest extends Request<String> {

    }

    static class SimpleRequestHandler implements RequestHandler<SimpleRequest> {

        @Override
        public void handle(SimpleRequest request) {
            request.setResponse("");
        }

    }

}
