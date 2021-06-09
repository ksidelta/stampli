package com.example.domain.context.stamps.command;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "stamp_business")
public class StampBusinessEntity {
    @EmbeddedId
    ClientBusinessId id;

    @OneToMany
    @JoinColumns({
            @JoinColumn(name = "clientId"),
            @JoinColumn(name = "businessId")
    })
    List<Stamp> stamps;

    public StampBusinessEntity addStamp() {
        stamps.add(Stamp.create());
        return this;
    }

    // TODO: this function should be moved to Query part of Stamp Context.
    public Integer currentAmountOfStamps() {
        final var stampsQuantity = stamps.size() % 10; // there is a maximum of 10 stamps
        if (stampsQuantity == 0) return 10;
        return stampsQuantity;
    }

    public static StampBusinessEntity create(Integer clientId, Integer businessId) {
        final var entity = new StampBusinessEntity();
        entity.id = new ClientBusinessId(clientId, businessId);
        entity.stamps = new LinkedList<>();
        return entity;
    }
}
