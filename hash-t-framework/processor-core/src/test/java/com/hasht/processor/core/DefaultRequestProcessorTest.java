package com.hasht.processor.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DefaultRequestProcessorTest {

    @Test
    void registerHandler_whenTheHandlerIsDuplicated_shouldThrowDouplicatedHandlerException() {
        final DefaultRequestProcessor requestProcessor = new DefaultRequestProcessor();
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
        final DefaultRequestProcessor requestProcessor = new DefaultRequestProcessor();
        final SimpleRequestHandler handler = new SimpleRequestHandler();
        requestProcessor.setHandler(SimpleRequest.class, handler);

        assertDoesNotThrow(
                () ->
                        requestProcessor.setHandler(SimpleRequest.class, handler)
        );
    }

    @Test
    void process_whenHandlerNotFound_shouldThrowHandlerNotFoundException() {
        final SimpleRequest request = new SimpleRequest();
        final DefaultRequestProcessor requestProcessor = new DefaultRequestProcessor();

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
