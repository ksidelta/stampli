package com.example.domain.context.stamps.command;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "stamp_client")
public class StampClientAggregate {
    @Id
    Integer clientId;

    @OneToMany
    @JoinColumn(name = "clientId")
    Set<StampBusinessEntity> businesses;

    public void addStampToBusiness(Integer businessId) {
        final var clientBusinessId = new ClientBusinessId(clientId, businessId);
//        businesses.computeIfAbsent(clientBusinessId, (key) -> StampBusinessEntity.create(clientId, businessId));
//        businesses.computeIfPresent(clientBusinessId, (key, entity) -> entity.addStamp());
    }

    public Integer getStampsQuantity(Integer businessId) {
        return Optional.<StampBusinessEntity>ofNullable(null)
                .map(StampBusinessEntity::currentAmountOfStamps)
                .orElse(0);
    }

    public static StampClientAggregate create(Integer clientId) {
        final var aggr = new StampClientAggregate();
        aggr.clientId = clientId;
        return aggr;
    }
}
