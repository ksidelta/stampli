package com.example.modules.offers.service;

import java.awt.image.BufferedImage;
import java.util.List;

public interface OfferService {
    void addOffer(AddOfferDTO addOfferDTO);

    OfferDto getOffers(Integer businessId);

    record AddOfferDTO(Integer businessId, BufferedImage image) {
    }

    record OfferDto(List<BufferedImage> offers) {
    }
}
