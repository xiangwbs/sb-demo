package com.xwbing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class SbDemoApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SbDemoApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SbDemoApplication.class, args);
    }
}
