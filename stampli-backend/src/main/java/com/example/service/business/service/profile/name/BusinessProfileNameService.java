package com.example.service.business.service.profile.name;

import com.example.domain.business.entity.business.profile.BusinessName;
import com.example.domain.business.repository.BusinessRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BusinessProfileNameService {
    protected BusinessRepository businessRepository;

    public BusinessProfileNameService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    @Transactional
    public void updateName(Integer businessId, String name) {
        final var business = businessRepository.findById(businessId);
        business.usingBusinessProfile().updateBusinessName(new BusinessName(name));
        businessRepository.save(business);
    }

    public BusinessName getName(Integer businessId) {
        return businessRepository.findById(businessId).usingBusinessProfile().getBusinessName();
    }
}
