package com.example.service.stamps.challenge.service;

import com.example.domain.context.challenge.ClaimProof;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.GenericMessage;

public class WebsocketChallengeService implements ChallengeService {
    final ChallengeService challengeService;
    final SimpMessagingTemplate template;


    public WebsocketChallengeService(ChallengeService challengeService, SimpMessagingTemplate template) {
        this.challengeService = challengeService;
        this.template = template;
    }

    @Override
    public void createChallengeAggregate(Integer issuerId, Integer businessId) {
        this.challengeService.createChallengeAggregate(issuerId, businessId);
    }

    @Override
    public ChallengeTokenDTO acquireToken(Integer issuerId, Integer businessId) {
        return this.challengeService.acquireToken(issuerId, businessId);
    }

    @Override
    public ClaimProof claimToken(ChallengingTokenDTO challengingToken) {
        final var claimedToken = this.challengeService.claimToken(challengingToken);

        final var destination = "/api/business/challenge/" + challengingToken.getBusinessId() + "/" + challengingToken.getIssuerId();
        template.send(destination, new GenericMessage<>("RELOAD"));

        return claimedToken;
    }
}
