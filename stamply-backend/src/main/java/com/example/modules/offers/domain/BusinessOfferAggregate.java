package com.example.modules.offers.domain;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Type;

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
    @CollectionTable(name = "offer", joinColumns = @JoinColumn(name = "businessId"))
    List<Offer> offers;

    public Integer id() {
        return businessId;
    }

    public List<Offer> offers() {
        return offers;
    }

    public void addOffer(Offer businessOffer) {
        offers.add(businessOffer);
    }

    static public BusinessOfferAggregate create() {
        BusinessOfferAggregate businessAggregate = new BusinessOfferAggregate();
        businessAggregate.offers = new ArrayList<>();
        return businessAggregate;
    }
}
