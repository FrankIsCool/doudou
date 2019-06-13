package com.impl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@ImportResource(value = {"classpath:spring-dubbo-provider.xml"})
@MapperScan("com.impl.dao.*")
@ComponentScan(value = {"com.impl.*","com.common.*"})
@EnableScheduling
public class StoreApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }
}