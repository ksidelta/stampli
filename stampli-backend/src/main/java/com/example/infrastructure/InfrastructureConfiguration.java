package com.example.infrastructure;

import com.example.infrastructure.db.hibernate.ImageToBlobConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfrastructureConfiguration {
    @Bean
    public ImageToBlobConverter imageToBlobConverter() {
        return new ImageToBlobConverter();
    }
}
