package com.example.modules.business.service;

import com.example.modules.business.domain.BusinessAggregate;
import com.example.modules.business.domain.Owner;
import com.example.modules.business.repository.BusinessRepository;

import java.util.Optional;

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
