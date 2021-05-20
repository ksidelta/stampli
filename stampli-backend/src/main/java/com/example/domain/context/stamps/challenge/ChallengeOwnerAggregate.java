package com.example.domain.context.stamps.challenge;

public class ChallengeOwnerAggregate {
    Integer challengeId;

    Integer businessId;

    Integer issuerId;

    Integer randomNonce;

    Integer claimerId;

    protected ChallengeOwnerAggregate(Integer businessId, Integer issuerId, Integer randomNonce) {
        this.businessId = businessId;
        this.issuerId = issuerId;
        this.randomNonce = randomNonce;
    }

    protected ChallengeOwnerAggregate() {
    }

    public void claimBy(Integer claimerId) {
        assertCurrentClaimerIsEmpty(claimerId);
        this.claimerId = claimerId;
    }


    public static ChallengeOwnerAggregate createChallengeEntity(Integer businessId, Integer issuerId, Integer randomNonce) {
        return new ChallengeOwnerAggregate(businessId, issuerId, randomNonce);
    }

    public static void assertCurrentClaimerIsEmpty(Integer claimerId) {
        if (claimerId != null) {
            throw new IllegalStateException("You cannot reset owner of given challenge");
        }
    }

}
