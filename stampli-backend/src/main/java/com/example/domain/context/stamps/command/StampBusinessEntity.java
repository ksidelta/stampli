package com.example.domain.context.stamps.command;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "stamp_business")
public class StampBusinessEntity {
    @EmbeddedId
    StampBusinessId id;

    @OneToMany(mappedBy = "join")
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

    public static StampBusinessEntity create(StampClientAggregate clientId, Integer businessId) {
        final var entity = new StampBusinessEntity();
        entity.id = new StampBusinessId(clientId, businessId);
        entity.stamps = new LinkedList<>();
        return entity;
    }
}

@Embeddable
class StampBusinessId implements Serializable {
    @ManyToOne
    @JoinColumn(referencedColumnName = "clientId", name = "clientId")
    StampClientAggregate clientId;

    @Column(name = "businessId")
    Integer businessId;

    public StampBusinessId(StampClientAggregate clientId, Integer businessId) {
        this.clientId = clientId;
        this.businessId = businessId;
    }

    protected StampBusinessId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StampBusinessId that = (StampBusinessId) o;
        return Objects.equals(clientId, that.clientId) && Objects.equals(businessId, that.businessId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, businessId);
    }
}
