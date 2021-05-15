package com.example.service.business.service.profile;

import com.example.domain.business.entity.business.profile.BusinessName;
import com.example.service.business.service.profile.name.BusinessProfileNameService;
import org.springframework.stereotype.Service;

@Service
public class BusinessProfileServiceImpl implements BusinessProfileService {
    BusinessProfileNameService businessProfileNameService;

    public BusinessProfileServiceImpl(BusinessProfileNameService businessProfileNameService) {
        this.businessProfileNameService = businessProfileNameService;
    }

    public void updateName(Integer businessId, String name) {
        businessProfileNameService.updateName(businessId, name);
    }

    public BusinessName getName(Integer businessId) {
        return businessProfileNameService.getName(businessId);
    }
}
