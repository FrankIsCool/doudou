package com.impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ImportResource(value = {"classpath:spring-dubbo-provider.xml"})
public class UserApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}