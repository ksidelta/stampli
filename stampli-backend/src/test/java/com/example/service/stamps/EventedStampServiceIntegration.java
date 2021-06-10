package com.example.service.stamps;

import com.example.BaseTestConfiguration;
import com.example.common.db.AbstractDatabaseTest;
import com.example.domain.context.stamps.command.StampClientAggregateRepository;
import com.example.service.authentication.register.RegisterService;
import com.example.service.business.service.BusinessService;
import com.example.service.challenge.service.ChallengeService;
import com.example.service.challenge.service.ChallengingTokenDTO;
import com.example.service.stamps.service.StampService;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig({BaseTestConfiguration.class})
@Transactional
public class EventedStampServiceIntegration extends AbstractDatabaseTest {

    @Autowired
    StampClientAggregateRepository stampRepository;

    @Autowired
    BusinessService businessService;

    @Autowired
    ChallengeService challengeService;

    @Autowired
    RegisterService registerService;

    @Autowired
    StampService stampService;

    @Test
    public void whenUserIsCreatedThenStampAggregateIsCreated() {
        final var id = registerService.registerUser("mail@mail.com", "pusia1");

        assertThat(stampRepository.findByUserId(id).get(), notNullValue());
    }

    @Test
    public void whenChallengeIsClaimedThenStampAmountIsUpdated() {
        final var adminId = registerService.registerUser("admin@mail.com", "pusia1");
        final var businessId = businessService.createBusiness(adminId);
        final var token = challengeService.acquireToken(adminId, businessId);
        final var userId = registerService.registerUser("user@mail.com", "pusia1");

        challengeService.claimToken(new ChallengingTokenDTO(adminId, businessId, userId, token.getChallengeNonce()));

        MatcherAssert.assertThat(stampService.getNumberOfStamps(userId, businessId), equalTo(1));
    }
}