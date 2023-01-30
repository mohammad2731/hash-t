package com.ht.cqrs.config;

import com.ht.cqrs.core.RequestHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@ComponentScan(
        includeFilters = {
                @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = RequestHandler.class)
        })
@ComponentScans(
        @ComponentScan("com.ht.cqrs")
)
public @interface EnableCQRS {

    @AliasFor(annotation = ComponentScan.class)
    String[] basePackages() default {};

    @AliasFor(annotation = ComponentScan.class)
    String[] value() default {};

}
