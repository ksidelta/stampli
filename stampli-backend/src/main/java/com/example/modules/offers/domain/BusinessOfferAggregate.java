package com.example.modules.offers.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "business_offer")
public class BusinessOfferAggregate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer businessId;

    @ElementCollection
    List<Offer> offers;

    public void addOffer(Offer businessOffer) {
        offers.add(businessOffer);
    }

    static public BusinessOfferAggregate create() {
        BusinessOfferAggregate businessAggregate = new BusinessOfferAggregate();
        businessAggregate.offers = new ArrayList<>();
        return businessAggregate;
    }
}
