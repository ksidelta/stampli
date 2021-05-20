package com.example.service.business.service.profile.image;

import com.example.domain.context.business.entity.business.profile.BusinessLogo;
import com.example.domain.context.business.repository.BusinessRepository;
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
