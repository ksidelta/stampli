package com.example.domain.context.challenge;

import com.example.infrastructure.domain.events.AbstractEventedAggregate;

import java.security.SecureRandom;

public class ChallengeOwnerAggregate extends AbstractEventedAggregate {
    Integer businessId;

    Integer issuerId;

    boolean isChallengeInProgress;

    CurrentChallenge currentChallenge;

    protected ChallengeOwnerAggregate(Integer businessId, Integer issuerId) {
        this.businessId = businessId;
        this.issuerId = issuerId;
        this.isChallengeInProgress = false;
        this.currentChallenge = CurrentChallenge.emptyChallenge;
    }

    public void claimBy(Integer claimerId, Integer challengeNonce) {
        assertCorrectClaim(claimerId, challengeNonce);
        this.isChallengeInProgress = false;
        this.currentChallenge = CurrentChallenge.emptyChallenge;

        this.registerEvent(new ChallengeClaimedEvent(claimerId, issuerId, businessId));
    }

    public CurrentChallenge acquireChallenge(SecureRandom secureRandom) {
        this.currentChallenge = new CurrentChallenge(secureRandom.nextInt());
        this.isChallengeInProgress = true;
        return this.currentChallenge;
    }

    public static ChallengeOwnerAggregate createChallengeEntity(Integer businessId, Integer issuerId, Integer randomNonce) {
        return new ChallengeOwnerAggregate(businessId, issuerId);
    }

    public void assertCorrectClaim(Integer claimerId, Integer challengeNonce) {
        if (!this.isChallengeInProgress) {
            throw new IllegalStateException("Challenge is not in progress");
        }

        if (!this.currentChallenge.randomNonce.equals(challengeNonce)) {
            throw new IllegalArgumentException("Wrong nonce for challenge");
        }
    }

}
