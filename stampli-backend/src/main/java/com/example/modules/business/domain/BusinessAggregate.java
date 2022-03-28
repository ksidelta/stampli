package com.example.modules.business.domain;

import com.example.infrastructure.domain.events.AbstractEventedAggregate;
import com.example.modules.business.domain.profile.BusinessProfile;
import com.example.modules.business.domain.profile.BusinessProfile.BusinessLogo;
import com.example.modules.business.domain.profile.BusinessProfile.BusinessName;
import com.example.modules.business.service.events.CreatedBusinessEvent;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

@Entity
@Table(name = "business")
public class BusinessAggregate extends AbstractEventedAggregate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    Owner owner;

    @Embedded
    BusinessProfile businessProfile;

    protected BusinessAggregate() {
    }

    public BusinessProfile usingBusinessProfile() {
        return this.businessProfile;
    }

    public static BusinessAggregate createBusinessAggregate(Owner owner) {
        final var businessAggregate = new BusinessAggregate();

        final var profile = new BusinessProfile();
        profile.updateBusinessLogo(BusinessLogo.createBusinessLogo(defaultImage())); // TODO: make it some placeholder
        profile.updateBusinessName(new BusinessName("???"));

        businessAggregate.businessProfile = profile;
        businessAggregate.owner = owner;

        return businessAggregate;
    }

    public Owner owner() {
        return owner;
    }

    public Integer getId() {
        return id;
    }

    @PostPersist
    public void addCreatedEvent() {
        this.registerEvent(new CreatedBusinessEvent(getId(), owner.getOwnerId()));
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

    @Embeddable
    public static class Owner {
        @Column(name = "owner_id")
        Integer ownerId;

        protected Owner() {
        }

        public Owner(Integer ownerId) {
            this.ownerId = ownerId;
        }

        public Integer getOwnerId() {
            return ownerId;
        }

        protected void setOwnerId(Integer ownerId) {
            this.ownerId = ownerId;
        }
    }
}
