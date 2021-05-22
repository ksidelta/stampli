package com.example.service.stamps.challenge.service;

import com.example.BaseTestConfiguration;
import com.example.common.db.AbstractDatabaseTest;
import com.example.domain.context.challenge.ChallengeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
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

    @Autowired
    ChallengeService challengeService;

    @Autowired
    ChallengeRepository challengeRepository;

    @Test
    public void whenChallengeIsCreatedThenSucceeds() {
        challengeService.createChallengeAggregate(1, 2);

        assertThat(challengeRepository.findByIssuerAndBusiness(1, 2), is(not(Optional.empty())));
    }

    @Test
    public void givenCreatedAggregateWhenChallengeClaimedThenFails() {
        challengeService.createChallengeAggregate(1, 3);

        assertThrows(
                IllegalStateException.class,
                () -> challengeService.claimToken(new ChallengingTokenDTO(1, 3, 0, 0))
        );
    }

    @Test
    public void givenCreatedAggregateWithChallengeWhenChallengeClaimedThenItReturnsProof() {
        challengeService.createChallengeAggregate(1, 2);
        final var token = challengeService.acquireToken(1, 2);

        final var proof = challengeService.claimToken(
                new ChallengingTokenDTO(1, 2, 3, token.getChallengeNonce())
        );

        assertThat(proof.getIssuerId(), equalTo(1));
        assertThat(proof.getBusinessId(), equalTo(2));
        assertThat(proof.getClaimerId(), equalTo(3));
    }

    @Test
    public void givenClaimedChallengeWhenChallengeClaimedThenItFails() {
        challengeService.createChallengeAggregate(1, 2);
        final var token = challengeService.acquireToken(1, 2);
        challengeService.claimToken(
                new ChallengingTokenDTO(1, 2, 3, token.getChallengeNonce())
        );

        assertThrows(IllegalStateException.class, () -> challengeService.claimToken(
                new ChallengingTokenDTO(1, 2, 3, token.getChallengeNonce())
        ));
    }
}

