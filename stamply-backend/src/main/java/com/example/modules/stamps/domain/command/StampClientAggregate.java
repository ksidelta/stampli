package com.example.modules.stamps.domain.command;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "stamp_client")
public class StampClientAggregate {
    @Id
    Integer clientId;

    @LazyCollection(LazyCollectionOption.EXTRA)
    @OneToMany(mappedBy = "id.clientId", cascade = CascadeType.ALL)
    @MapKey(name = "id")
    Map<StampBusinessId, StampBusinessEntity> businesses; // TODO: Change it to map later, but fuck it is hard, how to do it!?

    public void addStampToBusiness(Integer businessId) {
        final var clientBusinessId = new StampBusinessId(this, businessId);

        Optional.ofNullable(businesses.get(clientBusinessId))
                .ifPresentOrElse(
                        StampBusinessEntity::addStamp,
                        () -> businesses.put(clientBusinessId, StampBusinessEntity.create(this, businessId).addStamp())
                );
    }

    public Integer getStampsQuantity(Integer businessId) {
        return Optional.ofNullable(businesses.get(new StampBusinessId(this, businessId)))
                .map(StampBusinessEntity::currentAmountOfStamps)
                .orElse(0);
    }

    public static StampClientAggregate create(Integer clientId) {
        final var aggr = new StampClientAggregate();
        aggr.clientId = clientId;
        aggr.businesses = new HashMap<>();
        return aggr;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StampClientAggregate that = (StampClientAggregate) o;
        return Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId);
    }
}
