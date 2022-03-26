package com.example.modules.challenge.service;

import com.example.modules.challenge.domain.ClaimProof;
import org.springframework.messaging.simp.SimpMessagingTemplate;

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

        final var destination = "/business/challenge/" + challengingToken.businessId() + "/" + challengingToken.issuerId();
        template.convertAndSend(destination, challengingToken);

        return claimedToken;
    }
}
