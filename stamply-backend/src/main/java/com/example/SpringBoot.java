package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

@Import(AppConfig.class)
@SpringBootConfiguration
@EnableAutoConfiguration
public class SpringBoot {

    static public void main(String[] args) {
        SpringApplication.run(SpringBoot.class, args);
    }
}
