package com.example.modules.business.domain.profile;


import com.example.infrastructure.db.hibernate.ImageToBlobConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.awt.image.BufferedImage;
import java.util.Objects;

@Embeddable
public class BusinessProfile {
    @Embedded
    BusinessLogo businessLogo;

    @Embedded
    BusinessName businessName;

    public BusinessLogo getBusinessLogo() {
        return businessLogo;
    }

    public void updateBusinessLogo(BusinessLogo businessLogo) {
        this.businessLogo = businessLogo;
    }

    public BusinessName getBusinessName() {
        return businessName;
    }

    public void updateBusinessName(BusinessName businessName) {
        this.businessName = businessName;
    }

    @Embeddable
    public static class BusinessLogo {
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

    @Embeddable
    public static class BusinessName {
        @Column(name = "business_name")
        String name;

        protected BusinessName() {
        }

        public BusinessName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        protected void setName(String name) {
            this.name = name;
        }
    }
}
