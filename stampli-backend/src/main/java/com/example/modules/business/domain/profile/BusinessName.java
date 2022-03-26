package com.example.modules.business.domain.profile;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BusinessName {
    @Column(name = "business_name")
    String name;

    protected BusinessName() {
    }

    public BusinessName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }
}
