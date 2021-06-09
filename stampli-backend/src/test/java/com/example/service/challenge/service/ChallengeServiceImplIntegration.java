package com.example.service.challenge.service;

import com.example.BaseTestConfiguration;
import com.example.common.db.AbstractDatabaseTest;
import com.example.domain.context.challenge.ChallengeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig({BaseTestConfiguration.class})
@Transactional
public class ChallengeServiceImplIntegration extends AbstractDatabaseTest {
    public static final int ISSUER_ID = 1;
    public static final int BUSINESS_ID = 2;

    @Autowired
    ChallengeService challengeService;

    @Autowired
    ChallengeRepository challengeRepository;

    @Test
    public void whenChallengeIsCreatedThenSucceeds() {
        challengeService.createChallengeAggregate(ISSUER_ID, BUSINESS_ID);

        assertThat(challengeRepository.findByIssuerAndBusiness(ISSUER_ID, BUSINESS_ID), is(not(Optional.empty())));
    }

    @Test
    public void givenCreatedAggregateWhenChallengeClaimedThenFails() {
        challengeService.createChallengeAggregate(ISSUER_ID, 3);

        assertThrows(
                IllegalStateException.class,
                () -> challengeService.claimToken(new ChallengingTokenDTO(ISSUER_ID, 3, 0, 0))
        );
    }

    @Test
    public void givenCreatedAggregateWithChallengeWhenChallengeClaimedThenItReturnsProof() {
        challengeService.createChallengeAggregate(ISSUER_ID, BUSINESS_ID);
        final var token = challengeService.acquireToken(ISSUER_ID, BUSINESS_ID);

        final var proof = challengeService.claimToken(
                new ChallengingTokenDTO(ISSUER_ID, BUSINESS_ID, 3, token.getChallengeNonce())
        );

        assertThat(proof.getIssuerId(), equalTo(ISSUER_ID));
        assertThat(proof.getBusinessId(), equalTo(BUSINESS_ID));
        assertThat(proof.getClaimerId(), equalTo(3));
    }

    @Test
    public void givenClaimedChallengeWhenChallengeClaimedThenItFails() {
        challengeService.createChallengeAggregate(ISSUER_ID, BUSINESS_ID);
        final var token = challengeService.acquireToken(ISSUER_ID, BUSINESS_ID);
        challengeService.claimToken(
                new ChallengingTokenDTO(ISSUER_ID, BUSINESS_ID, 3, token.getChallengeNonce())
        );

        assertThrows(IllegalStateException.class, () -> challengeService.claimToken(
                new ChallengingTokenDTO(ISSUER_ID, BUSINESS_ID, 3, token.getChallengeNonce())
        ));
    }
}

