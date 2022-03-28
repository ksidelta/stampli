package com.example.modules.offers.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BusinessOfferAggregate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer businessId;

    @ElementCollection
    private List<BusinessOffer> offers;

    public void addOffer(BusinessOffer businessOffer) {
        offers.add(businessOffer);
    }

    static public BusinessOfferAggregate create() {
        BusinessOfferAggregate businessAggregate = new BusinessOfferAggregate();
        businessAggregate.offers = new ArrayList<>();
        return businessAggregate;
    }
}
