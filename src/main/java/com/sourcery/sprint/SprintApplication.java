package com.sourcery.sprint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class SprintApplication {

    public static void main(String[] args) {
        SpringApplication.run(SprintApplication.class, args);
    }
}
