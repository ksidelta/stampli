package com.example.service.business.service;

import com.example.service.business.service.events.CreatedBusinessEvent;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;

public class EventedBusinessService implements BusinessService {
    BusinessService businessService;
    final ApplicationEventPublisher eventPublisher;

    public EventedBusinessService(BusinessService businessService, ApplicationEventPublisher eventPublisher) {
        this.businessService = businessService;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Integer createBusiness(Integer userId) {
        final var businessId = this.businessService.createBusiness(userId);

        eventPublisher.publishEvent(new CreatedBusinessEvent(businessId, userId));

        return businessId;
    }

    @Override
    public Optional<BusinessDto> findBusinessByUserId(Integer userId) {
        return this.findBusinessByUserId(userId);
    }
}
