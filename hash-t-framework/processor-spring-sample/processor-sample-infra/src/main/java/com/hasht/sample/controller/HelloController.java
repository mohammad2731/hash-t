package com.hasht.sample.controller;

import com.hasht.processor.core.AbstractRequestProcessor;
import com.hasht.sample.HelloRequestHandler.HelloRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private final AbstractRequestProcessor processor;

    public HelloController(AbstractRequestProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    ResponseEntity<String> sayHello() {
        final HelloRequest request = new HelloRequest();
        processor.process(request);
        return ResponseEntity.ok(request.getResponse());
    }

}
