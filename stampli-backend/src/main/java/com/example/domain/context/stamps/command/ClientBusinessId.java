package com.example.domain.context.stamps.command;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ClientBusinessId implements Serializable {
    Integer clientId;
    Integer businessId;

    public ClientBusinessId(Integer clientId, Integer businessId) {
        this.clientId = clientId;
        this.businessId = businessId;
    }

    protected ClientBusinessId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientBusinessId that = (ClientBusinessId) o;
        return Objects.equals(clientId, that.clientId) && Objects.equals(businessId, that.businessId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, businessId);
    }
}
