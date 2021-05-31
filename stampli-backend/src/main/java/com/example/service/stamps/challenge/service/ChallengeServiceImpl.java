package com.example.service.stamps.challenge.service;

import com.example.domain.context.challenge.ChallengeOwnerAggregate;
import com.example.domain.context.challenge.ChallengeRepository;
import com.example.domain.context.challenge.ClaimProof;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.security.SecureRandom;

@Transactional
public class ChallengeServiceImpl implements ChallengeService {
    final ChallengeRepository challengeRepository;
    final SecureRandom secureRandom;

    public ChallengeServiceImpl(ChallengeRepository challengeRepository, SecureRandom secureRandom) {
        this.challengeRepository = challengeRepository;
        this.secureRandom = secureRandom;
    }

    @Override
    public void createChallengeAggregate(Integer issuerId, Integer businessId) {
        final var entity = ChallengeOwnerAggregate.createChallengeEntity(businessId, issuerId);
        challengeRepository.save(entity);
    }

    @Override
    @Transactional
    public ChallengeTokenDTO acquireToken(Integer issuerId, Integer businessId) {
        final var entity = challengeRepository
                .findByIssuerAndBusiness(issuerId, businessId)
                .get();

        final var nextChallenge = entity.acquireChallenge(secureRandom::nextInt);

        challengeRepository.save(entity);

        return new ChallengeTokenDTO(issuerId, businessId, nextChallenge.nonce());
    }

    @Override
    public ClaimProof claimToken(ChallengingTokenDTO challengingToken) {
        final var challenge = challengeRepository.findByIssuerAndBusiness(
                challengingToken.getIssuerId(),
                challengingToken.getBusinessId()
        ).get();

        final var claimedToken =
                challenge.claimBy(challengingToken.getClaimerId(), challengingToken.getChallengeNonce());

        challengeRepository.save(challenge);

        return claimedToken;
    }
}
