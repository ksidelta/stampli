package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(AppConfig.class)
@SpringBootConfiguration
public class SpringBoot {

    static public void main(String[] args) {
        SpringApplication.run(SpringBoot.class, args);
    }
}
