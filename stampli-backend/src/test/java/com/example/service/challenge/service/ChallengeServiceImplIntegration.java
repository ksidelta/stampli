package com.example.service.challenge.service;

import com.example.BaseTestConfiguration;
import com.example.common.db.AbstractDatabaseTest;
import com.example.modules.challenge.domain.ChallengeRepository;
import com.example.modules.challenge.service.ChallengeService;
import com.example.modules.challenge.service.ChallengingTokenDTO;
import com.example.modules.stamps.service.StampService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig({BaseTestConfiguration.class})
public class ChallengeServiceImplIntegration extends AbstractDatabaseTest {
    public static final int ISSUER_ID = 1;
    public static final int BUSINESS_ID = 2;
    public static final int CLAIMER_ID = 3;

    @Autowired
    ChallengeService challengeService;

    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    StampService stampService;

    @BeforeEach
    public void create() {
        stampService.createStampsAggregateForClient(CLAIMER_ID);
    }

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
                new ChallengingTokenDTO(ISSUER_ID, BUSINESS_ID, CLAIMER_ID, token.getChallengeNonce())
        );

        assertThat(proof.getIssuerId(), equalTo(ISSUER_ID));
        assertThat(proof.getBusinessId(), equalTo(BUSINESS_ID));
        assertThat(proof.getClaimerId(), equalTo(CLAIMER_ID));
    }

    @Test
    public void givenClaimedChallengeWhenChallengeClaimedThenItFails() {
        challengeService.createChallengeAggregate(ISSUER_ID, BUSINESS_ID);
        final var token = challengeService.acquireToken(ISSUER_ID, BUSINESS_ID);
        challengeService.claimToken(
                new ChallengingTokenDTO(ISSUER_ID, BUSINESS_ID, CLAIMER_ID, token.getChallengeNonce())
        );

        assertThrows(IllegalStateException.class, () -> challengeService.claimToken(
                new ChallengingTokenDTO(ISSUER_ID, BUSINESS_ID, CLAIMER_ID, token.getChallengeNonce())
        ));
    }
}

