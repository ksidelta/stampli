package com.example.service.business.service;

import com.example.domain.business.entity.business.BusinessAggregate;
import com.example.domain.business.entity.owner.Owner;
import com.example.domain.business.repository.BusinessRepository;
import com.example.service.business.service.events.CreatedBusinessEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusinessServiceImpl implements BusinessService {
    BusinessRepository businessRepository;

    public BusinessServiceImpl(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    @Override
    public Integer createBusiness(Integer userId) {
        BusinessAggregate businessAggregate = BusinessAggregate.createBusinessAggregate(new Owner(userId));
        this.businessRepository.save(businessAggregate);

        return businessAggregate.getId();
    }

    @Override
    public Optional<BusinessDto> findBusinessByUserId(Integer userId) {
        return this.businessRepository.findByOwnerId(userId)
                .map(x -> new BusinessDto(x.getId(), x.usingBusinessProfile().getBusinessName().getName()));
    }
}
