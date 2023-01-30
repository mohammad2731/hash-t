package com.ht.cqrs.test.config;

import com.ht.cqrs.config.EnableCQRS;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCQRS(basePackages = "com.ht.cqrs.test")
public class HashTCQRSConfiguration {

}
