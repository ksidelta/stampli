package com.example.service.stamps.challenge.service;

import com.example.domain.context.challenge.ClaimProof;

public interface ChallengeService {
    void createChallengeAggregate(Integer issuerId, Integer businessId);

    ChallengeTokenDTO acquireToken(Integer issuerId, Integer businessId);

    ClaimProof claimToken(ChallengingTokenDTO challengingToken);
}
