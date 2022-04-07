package com.example.modules.challenge.service;

import com.example.modules.challenge.domain.ClaimProof;

public interface ChallengeService {
    void createChallengeAggregate(Integer issuerId, Integer businessId);

    ChallengeTokenDTO acquireToken(Integer issuerId, Integer businessId);

    ClaimProof claimToken(ChallengingTokenDTO challengingToken);
}
