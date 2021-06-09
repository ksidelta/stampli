package com.example.domain.context.stamps.command;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "stamp_client")
public class StampClientAggregate {
    @Id
    Integer clientId;

    @OneToMany
    Map<ClientBusinessId, StampBusinessEntity> businesses;

    public void addStampToBusiness(Integer businessId) {
        final var clientBusinessId = new ClientBusinessId(clientId, businessId);
        businesses.computeIfAbsent(clientBusinessId, (key) -> StampBusinessEntity.create(clientId, businessId));
        businesses.computeIfPresent(clientBusinessId, (key, entity) -> entity.addStamp());
    }

    public Integer getStampsQuantity(Integer businessId) {
        return Optional.ofNullable(businesses.get(new ClientBusinessId(clientId, businessId)))
                .map(StampBusinessEntity::currentAmountOfStamps)
                .orElse(0);
    }

    public static StampClientAggregate create(Integer clientId) {
        final var aggr = new StampClientAggregate();
        aggr.clientId = clientId;
        aggr.businesses = new HashMap<>();
        return aggr;
    }
}
