package com.example.modules.offers.domain;

import com.example.infrastructure.db.hibernate.ImageToBlobConverter;
import com.example.modules._common.domain.exceptions.InvalidImageException;

import javax.persistence.*;
import java.awt.image.BufferedImage;

@Embeddable
@Table(name = "offer")
public class Offer {
    @Column
    @Convert(converter = ImageToBlobConverter.class)
    BufferedImage image;

    public BufferedImage image() {
        return image;
    }

    static public Offer create(BufferedImage image) {
        InvalidImageException.assertRatio(image, 3, 2);
        var offer = new Offer();
        offer.image = image;
        return offer;
    }
}
