package com.hasht.processor.core;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class RequestProcessorTest {

    @Test
    void process_shouldHandleRequest() {
        final Request<Object> request = new Request<>();
        final var handler = mock(RequestHandler.class);
        final AbstractRequestProcessor processor = new AbstractRequestProcessor() {
            @Override
            protected <T, R extends Request<T>> RequestHandler<R> getHandler(R request) {
                return handler;
            }
        };

        processor.process(request);

        verify(handler, times(1)).handle(request);
    }

}
