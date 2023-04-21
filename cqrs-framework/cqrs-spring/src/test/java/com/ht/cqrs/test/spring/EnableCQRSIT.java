package com.ht.cqrs.test.spring;

import com.ht.cqrs.core.Request;
import com.ht.cqrs.core.RequestHandler;
import com.ht.cqrs.spring.EnableCQRS;
import com.ht.cqrs.spring.HandlerConfiguration;
import com.ht.cqrs.test.infra.HashTIntegrationTest;
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
        assertThat(context.getBean(HandlerConfiguration.class)).isNotNull();
    }

    @Test
    void springContext_shouldHaveABeanAnnotatedByEnableCQRS() {
        assertThat(context.getBeansWithAnnotation(EnableCQRS.class)).isNotNull();
    }

    private static class SampleRequestHandler implements RequestHandler<Request<String>> {

        @Override
        public void handle(Request<String> request) {

        }

    }

}
