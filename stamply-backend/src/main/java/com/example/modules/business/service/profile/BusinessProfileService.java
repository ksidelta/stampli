package com.example.modules.business.service.profile;

import com.example.modules.business.domain.profile.BusinessProfile;

import java.awt.image.BufferedImage;
import java.util.List;

public interface BusinessProfileService {
    void updateName(Integer businessId, String name);

    BusinessProfile.BusinessName getName(Integer businessId);

    void updateLogo(Integer businessId, BufferedImage logo);

    BufferedImage getLogo(Integer businessId);

    void updateOffers(Integer businessId, List<BufferedImage> offers);

    List<BufferedImage> getOffers(Integer businessId);
}
