package com.hasht.processor.test.spring;

import com.hasht.processor.spring.HandlerConfiguration;
import com.hasht.processor.test.infra.HashTIntegrationTest;
import com.hasht.processor.test.spring.sample.SampleRequestHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.*;

@HashTIntegrationTest
class EnableProcessorIT {

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
        assertThat(context.getBeansWithAnnotation(com.hasht.processor.spring.EnableProcessor.class)).isNotNull();
    }

}
