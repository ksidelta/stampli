package com.example.modules.business.service.profile;

import com.example.modules.business.domain.profile.BusinessProfile;
import com.example.modules.business.repository.BusinessRepository;
import com.example.modules.business.service.profile.image.BusinessProfileLogoService;
import com.example.modules.business.service.profile.name.BusinessProfileNameService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BusinessProfileServiceImpl implements BusinessProfileService {
    final BusinessProfileNameService businessProfileNameService;
    final BusinessProfileLogoService businessProfileLogoService;
    final BusinessRepository businessRepository;


    public BusinessProfileServiceImpl(
            BusinessProfileNameService businessProfileNameService,
            BusinessProfileLogoService businessProfileLogoService,
            BusinessRepository businessRepository) {
        this.businessProfileNameService = businessProfileNameService;
        this.businessProfileLogoService = businessProfileLogoService;
        this.businessRepository = businessRepository;
    }

    public void updateName(Integer businessId, String name) {
        businessProfileNameService.updateName(businessId, name);
    }

    public BusinessProfile.BusinessName getName(Integer businessId) {
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

    @Override
    public void updateOffers(Integer businessId, List<BufferedImage> offers) {
        var business = businessRepository.findById(businessId);
        business.usingBusinessProfile().setOffers(offers);
        businessRepository.save(business);
    }

    @Override
    public List<BufferedImage> getOffers(Integer businessId) {
        var business = businessRepository.findById(businessId);
        return business.usingBusinessProfile().getOffers().stream()
                .map(BusinessProfile.BusinessImage::getData)
                .collect(Collectors.toList());
    }


}
