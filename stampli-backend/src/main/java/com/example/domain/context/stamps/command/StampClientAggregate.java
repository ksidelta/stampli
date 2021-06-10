package com.example.domain.context.stamps.command;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "stamp_client")
public class StampClientAggregate {
    @Id
    Integer clientId;

    @OneToMany(mappedBy = "id.clientId")
    List<StampBusinessEntity> businesses; // TODO: Change it to map later, but fuck it is hard, how to do it!?

    public void addStampToBusiness(Integer businessId) {
        final var clientBusinessId = new StampBusinessId(this, businessId);

        businesses.stream().filter(x -> x.id.businessId.equals(businessId)).findAny()
                .ifPresentOrElse(
                        StampBusinessEntity::addStamp,
                        () -> businesses.add(StampBusinessEntity.create(this, businessId).addStamp())
                );
    }

    public Integer getStampsQuantity(Integer businessId) {
        return businesses.stream().filter(x -> x.id.businessId.equals(businessId)).findAny()
                .map(StampBusinessEntity::currentAmountOfStamps)
                .orElse(0);
    }

    public static StampClientAggregate create(Integer clientId) {
        final var aggr = new StampClientAggregate();
        aggr.clientId = clientId;
        aggr.businesses = new LinkedList<>();
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
