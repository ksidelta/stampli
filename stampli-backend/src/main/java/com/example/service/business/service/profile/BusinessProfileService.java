package com.example.service.business.service.profile;

import com.example.domain.business.entity.business.profile.BusinessName;
import com.example.service.business.service.profile.name.BusinessProfileNameService;
import org.springframework.stereotype.Service;

@Service
public class BusinessProfileService {
    BusinessProfileNameService businessProfileNameService;

    public BusinessProfileService(BusinessProfileNameService businessProfileNameService) {
        this.businessProfileNameService = businessProfileNameService;
    }

    public void updateName(Integer businessId, String name) {
        businessProfileNameService.updateName(businessId, name);
    }

    public BusinessName getName(Integer businessId) {
        return businessProfileNameService.getName(businessId);
    }
}
