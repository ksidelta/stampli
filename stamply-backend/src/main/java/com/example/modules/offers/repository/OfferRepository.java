package com.example.modules.offers.repository;

import com.example.modules.offers.domain.BusinessOfferAggregate;

public interface OfferRepository {
    BusinessOfferAggregate findById(Integer businessId);

    void save(BusinessOfferAggregate businessOfferAggregate);
}
