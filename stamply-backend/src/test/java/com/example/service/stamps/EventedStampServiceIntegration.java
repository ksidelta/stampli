package com.example.service.stamps;

import com.example.BaseTestConfiguration;
import com.example.common.db.AbstractDatabaseTest;
import com.example.modules.stamps.domain.command.StampClientAggregateRepository;
import com.example.modules.authentication.service.register.RegisterService;
import com.example.modules.business.service.BusinessService;
import com.example.modules.challenge.service.ChallengeService;
import com.example.modules.challenge.service.ChallengingTokenDTO;
import com.example.modules.stamps.service.StampService;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig({BaseTestConfiguration.class})
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
    @Disabled
    public void whenChallengeIsClaimedThenStampAmountIsUpdated() {
        final var adminId = registerService.registerUser("admin@mail.com", "pusia1");
        final var businessId = businessService.createBusiness(adminId);
        final var token = challengeService.acquireToken(adminId, businessId);
        final var userId = registerService.registerUser("user@mail.com", "pusia1");

        challengeService.claimToken(new ChallengingTokenDTO(adminId, businessId, userId, token.challengeNonce()));

        MatcherAssert.assertThat(stampService.getNumberOfStamps(userId, businessId), equalTo(1));
    }
}
