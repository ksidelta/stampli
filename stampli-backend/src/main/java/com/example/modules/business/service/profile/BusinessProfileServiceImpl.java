package com.example.modules.business.service.profile;

import com.example.modules.business.domain.profile.BusinessName;
import com.example.modules.business.service.profile.image.BusinessProfileLogoService;
import com.example.modules.business.service.profile.name.BusinessProfileNameService;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class BusinessProfileServiceImpl implements BusinessProfileService {
    final BusinessProfileNameService businessProfileNameService;
    final BusinessProfileLogoService businessProfileLogoService;


    public BusinessProfileServiceImpl(
            BusinessProfileNameService businessProfileNameService,
            BusinessProfileLogoService businessProfileLogoService
    ) {
        this.businessProfileNameService = businessProfileNameService;
        this.businessProfileLogoService = businessProfileLogoService;
    }

    public void updateName(Integer businessId, String name) {
        businessProfileNameService.updateName(businessId, name);
    }

    public BusinessName getName(Integer businessId) {
        return businessProfileNameService.getName(businessId);
    }

    @Override
    public void updateLogo(Integer businessId, BufferedImage logo) {
        businessProfileLogoService.updateLogo(businessId, logo);
    }

    @Override
    public BufferedImage getLogo(Integer businessId) {
        return businessProfileLogoService.getLogo(businessId);
    }
}
