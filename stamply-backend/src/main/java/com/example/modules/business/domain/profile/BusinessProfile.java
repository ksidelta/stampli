package com.example.modules.business.domain.profile;


import com.example.infrastructure.db.hibernate.ImageToBlobConverter;
import com.example.modules._common.domain.exceptions.InvalidImageException;

import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Embeddable
public class BusinessProfile {
    @Embedded
    BusinessLogo businessLogo;

    @Embedded
    BusinessName businessName;

    @ElementCollection
    @CollectionTable(name = "business_images", joinColumns = @JoinColumn(name = "businessId"))
    @OrderColumn(name = "orderNum")
    List<BusinessImage> offers;

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

    public List<BusinessImage> getOffers() {
        return offers;
    }

    public void setOffers(List<BufferedImage> images) {
        this.offers = images.stream().map(BusinessImage::createOffer).collect(toList());
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

    @Embeddable
    public static class BusinessImage {
        @Column
        @Convert(converter = ImageToBlobConverter.class)
        BufferedImage data;

        public BusinessImage() {
        }

        public BufferedImage getData() {
            return data;
        }

        static BusinessImage createImage(BufferedImage image) {
            var businessImage = new BusinessImage();
            businessImage.data = image;
            return businessImage;
        }

        static BusinessImage createOffer(BufferedImage image) {
            InvalidImageException.assertRatio(image, 3, 2);
            return createImage(image);
        }
    }

}
