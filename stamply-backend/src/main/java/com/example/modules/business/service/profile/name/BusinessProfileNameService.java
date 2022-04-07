package com.example.modules.business.service.profile.name;

import com.example.modules.business.domain.profile.BusinessProfile;
import com.example.modules.business.repository.BusinessRepository;
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
        business.usingBusinessProfile().updateBusinessName(new BusinessProfile.BusinessName(name));
        businessRepository.save(business);
    }

    public BusinessProfile.BusinessName getName(Integer businessId) {
        return businessRepository.findById(businessId).usingBusinessProfile().getBusinessName();
    }
}
