package com.example.infrastructure.bson;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BsonConfiguration {
    @Bean
    public BsonMessageConverter bsonMessageConverter() {
        return new BsonMessageConverter();
    }
}
