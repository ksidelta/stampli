package com.example.modules.offers.service;

import com.example.BaseTestConfiguration;
import com.example.common.db.AbstractDatabaseTest;
import com.example.modules.offers.domain.BusinessOfferAggregate;
import com.example.modules.offers.repository.OfferRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import javax.imageio.ImageIO;
import java.io.IOException;


@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig({BaseTestConfiguration.class})
class OfferServiceImplIntegration extends AbstractDatabaseTest {

    @Autowired
    OfferService offerService;

    @Autowired
    OfferRepository offerRepository;

//    @Test
//    void givenCreatedBusinessOfferAggregateWhenOfferIsAddedThenItSucceeds() throws IOException {
//        var business = BusinessOfferAggregate.create();
//        offerRepository.save(business);
//
//        var image = ImageIO.read(OfferServiceImplIntegration.class.getResourceAsStream("/image/3:2.png"));
//
//        offerService.addOffer(new OfferService.AddOfferDTO(business.id(), image));
//    }
//
//    @Test
//    void givenCreatedBusinessOfferAggregateWhenOfferIsAddedThenItSucceeds() throws IOException {
//        var business = BusinessOfferAggregate.create();
//        offerRepository.save(business);
//        var image = ImageIO.read(OfferServiceImplIntegration.class.getResourceAsStream("/image/3:2.png"));
//        offerService.addOffer(new OfferService.AddOfferDTO(business.id(), image));
//
//        offerService.getOffers(business.id());
//
//    }
}