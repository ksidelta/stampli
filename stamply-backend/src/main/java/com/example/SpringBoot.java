package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(AppConfig.class)
@Configuration
public class SpringBoot {
    static public void main(String[] args) throws Exception {
        SpringApplication.run(SpringBoot.class, args);
    }
}
