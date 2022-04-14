package com.example.modules.offers.service;

import com.example.modules.offers.domain.Offer;
import com.example.modules.offers.repository.OfferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.stream.Collectors.toList;

@Service
public class OfferServiceImpl implements OfferService {
    OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    @Transactional
    public void addOffer(AddOfferDTO addOfferDTO) {
        var business = offerRepository.findById(addOfferDTO.businessId());
        business.addOffer(Offer.create(addOfferDTO.image()));
        offerRepository.save(business);
    }

    @Override
    @Transactional
    public OfferDto getOffers(Integer businessId) {
        var business = offerRepository.findById(businessId);
        var offers = business.offers().stream()
                .map(Offer::image)
                .collect(toList());
        return new OfferDto(offers);
    }
}
