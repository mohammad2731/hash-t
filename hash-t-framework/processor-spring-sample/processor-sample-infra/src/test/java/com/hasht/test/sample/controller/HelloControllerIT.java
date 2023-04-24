package com.hasht.test.sample.controller;

import com.hasht.sample.HelloRequestHandler;
import com.hasht.sample.Main;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Main.class)
class HelloControllerIT {

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void greetingShouldReturnDefaultMessage() {
        final String url = "http://localhost:" + port + "/hello";

        final String response = this.restTemplate.getForObject(url, String.class);

        assertThat(response)
                .isEqualTo(HelloRequestHandler.DEFAULT_RESPONSE);
    }

}
