package com.example.modules.challenge.domain;

import com.example.infrastructure.domain.events.AbstractEventedAggregate;

import javax.persistence.*;
import java.util.Objects;
import java.util.function.Supplier;

@Entity
@Table(name = "challenge")
public class ChallengeOwnerAggregate extends AbstractEventedAggregate {
    @EmbeddedId
    ChallengeId challengeId;

    @Column(name = "in_progress")
    boolean isChallengeInProgress;

    @Embedded
    CurrentChallenge currentChallenge;

    protected ChallengeOwnerAggregate() {
    }

    protected ChallengeOwnerAggregate(Integer businessId, Integer issuerId) {
        this.challengeId = new ChallengeId(businessId, issuerId);
        this.isChallengeInProgress = false;
        this.currentChallenge = CurrentChallenge.emptyChallenge;
    }

    public ClaimProof claimBy(Integer claimerId, Integer challengeNonce) {
        assertCorrectClaim(claimerId, challengeNonce);

        this.isChallengeInProgress = false;
        this.currentChallenge = CurrentChallenge.emptyChallenge;

        this.registerEvent(new ChallengeClaimedEvent(claimerId, this.challengeId.issuerId, this.challengeId.businessId));
        return new ClaimProof(claimerId, challengeId.issuerId, challengeId.businessId);
    }

    public CurrentChallenge acquireChallenge(Supplier<Integer> secureRandom) {
        this.currentChallenge = new CurrentChallenge(secureRandom.get());
        this.isChallengeInProgress = true;
        return this.currentChallenge;
    }

    public static ChallengeOwnerAggregate createChallengeEntity(Integer businessId, Integer issuerId) {
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

    public ChallengeId challengeId() {
        return challengeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChallengeOwnerAggregate that = (ChallengeOwnerAggregate) o;
        return Objects.equals(challengeId, that.challengeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(challengeId);
    }
}
