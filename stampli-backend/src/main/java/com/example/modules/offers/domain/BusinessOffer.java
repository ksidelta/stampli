package com.example.modules.offers.domain;

import com.example.infrastructure.db.hibernate.ImageToBlobConverter;
import com.example.modules._common.domain.exceptions.InvalidImageException;

import javax.persistence.*;
import java.awt.image.BufferedImage;

@Embeddable
public class BusinessOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @Convert(converter = ImageToBlobConverter.class)
    BufferedImage image;

    static BusinessOffer create(BufferedImage image) {
        InvalidImageException.assertRatio(image, 3, 2);
        var offer = new BusinessOffer();
        offer.image = image;
        return offer;
    }
}
