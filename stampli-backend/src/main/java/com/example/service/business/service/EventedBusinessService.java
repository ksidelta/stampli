package com.example.service.business.service;

import com.example.service.business.service.events.CreatedBusinessEvent;
import org.springframework.context.ApplicationEventPublisher;

import javax.transaction.Transactional;
import java.util.Optional;

public class EventedBusinessService implements BusinessService {
    BusinessService businessService;
    final ApplicationEventPublisher eventPublisher;

    public EventedBusinessService(BusinessService businessService, ApplicationEventPublisher eventPublisher) {
        this.businessService = businessService;
        this.eventPublisher = eventPublisher;
    }

    @Override
    @Transactional
    public Integer createBusiness(Integer userId) {
        final var businessId = this.businessService.createBusiness(userId);

        eventPublisher.publishEvent(new CreatedBusinessEvent(businessId, userId));

        return businessId;
    }

    @Override
    public Optional<BusinessDto> findBusinessByUserId(Integer userId) {
        return businessService.findBusinessByUserId(userId);
    }
}
