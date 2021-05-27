package com.example.controller.stamps.challenge;

public class ClaimProofDto {
    Integer claimerId;
    Integer issuerId;
    Integer businessId;

    public ClaimProofDto() {
    }

    public ClaimProofDto(Integer claimerId, Integer issuerId, Integer businessId) {
        this.claimerId = claimerId;
        this.issuerId = issuerId;
        this.businessId = businessId;
    }

    public Integer getClaimerId() {
        return claimerId;
    }

    public void setClaimerId(Integer claimerId) {
        this.claimerId = claimerId;
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
}
