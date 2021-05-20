package com.example.domain.context.stamps.challenge;

public class Issuer {
    public Issuer(Integer userId, Integer businessId) {
        this.userId = userId;
        this.businessId = businessId;
    }

    private final Integer userId;

    private final Integer businessId;

    public Integer getUserId() {
        return userId;
    }

    public Integer getBusinessId() {
        return businessId;
    }
}
