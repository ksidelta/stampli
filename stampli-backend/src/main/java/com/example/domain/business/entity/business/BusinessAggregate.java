package com.example.domain.business.entity.business;

import com.example.domain.business.entity.business.profile.BusinessLogo;
import com.example.domain.business.entity.business.profile.BusinessName;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

@Entity(name = "business")
public class BusinessAggregate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    BusinessProfile businessProfile;

    public BusinessProfile usingBusinessProfile() {
        return this.businessProfile;
    }

    public static BusinessAggregate createBusinessAggregate() {
        final var businessAggregate = new BusinessAggregate();

        final var profile = new BusinessProfile();
        profile.updateBusinessLogo(BusinessLogo.createBusinessLogo(defaultImage())); // TODO: make it some placeholder
        profile.updateBusinessName(new BusinessName("???"));

        businessAggregate.businessProfile = profile;

        return businessAggregate;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessAggregate that = (BusinessAggregate) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static BufferedImage defaultImage() {
        try {
            return ImageIO.read(BusinessAggregate.class.getResourceAsStream("/image/512x512.png"));
        } catch (IOException e) {
            throw new IllegalStateException("default image missing");
        }
    }
}
