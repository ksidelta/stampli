package com.example.controller.stamps.challenge;

public class AcquireTokenDto {
    Integer issuerId;
    Integer businessId;
    Integer nonce;

    public AcquireTokenDto(Integer issuerId, Integer businessId, Integer nonce) {
        this.issuerId = issuerId;
        this.businessId = businessId;
        this.nonce = nonce;
    }

    public AcquireTokenDto() {
    }

    public Integer getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(Integer issuerId) {
        this.issuerId = issuerId;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getNonce() {
        return nonce;
    }

    public void setNonce(Integer nonce) {
        this.nonce = nonce;
    }
}
