package com.example.service.business.service.profile;

import com.example.domain.business.entity.business.profile.BusinessName;

public interface BusinessProfileService {
    void updateName(Integer businessId, String name);

    BusinessName getName(Integer businessId);
}
