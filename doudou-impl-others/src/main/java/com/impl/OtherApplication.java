package com.impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(value = {"classpath:spring-dubbo-provider.xml"})
public class OtherApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(OtherApplication.class, args);
    }
}
