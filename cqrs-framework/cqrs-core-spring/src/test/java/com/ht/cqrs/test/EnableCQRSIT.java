package com.ht.cqrs.test;

import com.ht.cqrs.core.RequestHandler;
import com.ht.cqrs.test.config.HashTIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.*;

@HashTIntegrationTest
class EnableCQRSIT {

    @Autowired
    ApplicationContext context;

    @Test
    void requestHandler_shouldBeScannedBySpring() {
        assertThat(context.getBean(SampleRequestHandler.class)).isNotNull();
    }

    @Test
    void handlerConfiguration_shouldBeScannedBySpring() {
        assertThat(context.getBean(SampleRequestHandler.class)).isNotNull();
    }

    private static class SampleRequestHandler implements RequestHandler {

    }

}
