package com.example.service.business.service;

import com.example.domain.context.business.repository.BusinessRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class BusinessServiceConfiguration {

    @Bean
    public BusinessServiceImpl businessServiceImpl(BusinessRepository businessRepository) {
        return new BusinessServiceImpl(businessRepository);
    }

    @Bean
    @Primary
    public BusinessService businessService(BusinessServiceImpl businessServiceImpl, ApplicationEventPublisher eventPublisher) {
        return new EventedBusinessService(businessServiceImpl, eventPublisher);
    }
}
