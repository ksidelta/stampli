package com.example.service.business.service;

import com.example.BaseTestConfiguration;
import com.example.common.db.AbstractDatabaseTest;
import com.example.domain.context.challenge.ChallengeRepository;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.equalTo;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig({BaseTestConfiguration.class})
@Transactional
public class EventedBusinessServiceIntegration extends AbstractDatabaseTest {

    @Autowired
    BusinessService businessService;

    @Autowired
    ChallengeRepository challengeRepository;

    @Test
    public void whenBusinessIsCreatedThenChallengeOwnerIsCreated() {
        final var businessId = businessService.createBusiness(1);

        final var challenge = challengeRepository.findByIssuerAndBusiness(1, businessId).get();

        MatcherAssert.assertThat(challenge.challengeId().getBusinessId(), equalTo(businessId));
        MatcherAssert.assertThat(challenge.challengeId().getIssuerId(), equalTo(1));
    }
}
