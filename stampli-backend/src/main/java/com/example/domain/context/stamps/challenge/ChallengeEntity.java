package com.example.domain.context.stamps.challenge;

public class ChallengeEntity {
    Integer challengeId;

    Integer businessId;

    Integer issuerId;

    Integer randomNonce;

    Integer claimerId;

    protected ChallengeEntity(Integer businessId, Integer issuerId, Integer randomNonce) {
        this.businessId = businessId;
        this.issuerId = issuerId;
        this.randomNonce = randomNonce;
    }

    protected ChallengeEntity() {
    }

    public void claimBy(Integer claimerId) {
        this.claimerId = claimerId;
    }

    public static ChallengeEntity createChallengeEntity(Integer businessId, Integer issuerId, Integer randomNonce) {
        return new ChallengeEntity(businessId, issuerId, randomNonce);
    }
}
