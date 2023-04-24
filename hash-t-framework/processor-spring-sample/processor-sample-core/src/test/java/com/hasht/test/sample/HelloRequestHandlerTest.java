package com.hasht.test.sample;

import com.hasht.sample.HelloRequestHandler;
import com.hasht.sample.HelloRequestHandler.HelloRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class HelloRequestHandlerTest {

    @Test
    void shouldProcessRequest() {
        final HelloRequest request = new HelloRequest();

        new HelloRequestHandler().handle(request);

        assertThat(request.getResponse())
                .isEqualTo(HelloRequestHandler.DEFAULT_RESPONSE);
    }

}
