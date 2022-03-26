package com.example.modules.business.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Owner {
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
