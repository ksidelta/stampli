package com.example.domain.business.entity.owner;

import javax.persistence.Embeddable;

@Embeddable
public class Owner {
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
