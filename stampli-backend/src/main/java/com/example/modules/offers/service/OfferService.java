package com.example.modules.offers.service;

import java.awt.image.BufferedImage;

public interface OfferService {
    void addOffer(AddOfferDTO addOfferDTO);

    void getOffers(Integer businessId);

    record AddOfferDTO(Integer businessId, BufferedImage image) {
    }
}
