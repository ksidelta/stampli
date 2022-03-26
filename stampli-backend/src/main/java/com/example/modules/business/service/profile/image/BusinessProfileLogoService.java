package com.example.modules.business.service.profile.image;

import com.example.modules.business.domain.profile.BusinessLogo;
import com.example.modules.business.repository.BusinessRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.image.BufferedImage;

@Service
public class BusinessProfileLogoService {
    protected BusinessRepository businessRepository;

    public BusinessProfileLogoService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    @Transactional
    public void updateLogo(Integer businessId, BufferedImage logo) {
        final var business = businessRepository.findById(businessId);
        business.usingBusinessProfile().updateBusinessLogo(BusinessLogo.createBusinessLogo(logo));
        businessRepository.save(business);
    }

    public BufferedImage getLogo(Integer businessId) {
        return businessRepository.findById(businessId).usingBusinessProfile().getBusinessLogo().getImage();
    }
}
