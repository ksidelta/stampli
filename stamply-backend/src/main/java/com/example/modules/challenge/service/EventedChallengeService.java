package com.example.modules.challenge.service;


import com.example.modules.business.service.events.CreatedBusinessEvent;
import com.example.modules.challenge.domain.ClaimProof;
import org.springframework.context.event.EventListener;

public record EventedChallengeService(ChallengeService challengeService) implements ChallengeService {
    @EventListener(CreatedBusinessEvent.class)
    public void consumeCreatedBusinessEvent(CreatedBusinessEvent event) {
        this.createChallengeAggregate(event.ownerId(), event.businessId());
    }

    @Override
    public void createChallengeAggregate(Integer issuerId, Integer businessId) {
        challengeService.createChallengeAggregate(issuerId, businessId);
    }

    @Override
    public ChallengeTokenDTO acquireToken(Integer issuerId, Integer businessId) {
        return challengeService.acquireToken(issuerId, businessId);
    }

    @Override
    public ClaimProof claimToken(ChallengingTokenDTO challengingToken) {
        return challengeService.claimToken(challengingToken);
    }
}
