package com.example.modules.challenge.domain;

import java.util.Optional;

public interface ChallengeRepository {
    void save(ChallengeOwnerAggregate challengeOwnerAggregate);

    Optional<ChallengeOwnerAggregate> findByIssuerAndBusiness(Integer issuerId, Integer businessId);
}
