package com.hasht.sample;

import com.hasht.processor.spring.EnableProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessor
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
