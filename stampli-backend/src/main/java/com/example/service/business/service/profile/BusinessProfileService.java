package com.example.service.business.service.profile;

import com.example.domain.business.entity.business.profile.BusinessLogo;
import com.example.domain.business.entity.business.profile.BusinessName;

import java.awt.image.BufferedImage;

public interface BusinessProfileService {
    void updateName(Integer businessId, String name);

    BusinessName getName(Integer businessId);

    void updateLogo(Integer businessId, BufferedImage logo);

    BufferedImage getLogo(Integer businessId);
}
