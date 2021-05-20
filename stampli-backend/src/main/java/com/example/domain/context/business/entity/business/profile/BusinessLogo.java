package com.example.domain.context.business.entity.business.profile;

import com.example.infrastructure.db.hibernate.ImageToBlobConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import java.awt.image.BufferedImage;
import java.util.Objects;

@Embeddable
public class BusinessLogo {
    @Convert(converter = ImageToBlobConverter.class)
    @Column(name = "logo")
    BufferedImage image;

    protected BusinessLogo() {
    }

    public BufferedImage getImage() {
        return image;
    }

    public static BusinessLogo createBusinessLogo(BufferedImage image) {
        final var logo = new BusinessLogo();
        logo.image = image;
        return logo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessLogo that = (BusinessLogo) o;
        return Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(image);
    }
}
