package com.example.modules.business.service;

import com.example.modules.business.repository.BusinessRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class BusinessServiceConfiguration {
    @Bean
    @Primary
    public BusinessServiceImpl businessService(BusinessRepository businessRepository) {
        return new BusinessServiceImpl(businessRepository);
    }
}
