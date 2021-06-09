package com.example.service.stamps;

import com.example.BaseTestConfiguration;
import com.example.common.db.AbstractDatabaseTest;
import com.example.service.stamps.service.StampService;
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
public class StampServiceIntegration extends AbstractDatabaseTest {

    @Autowired
    StampService service;

    @Test
    public void whenStampAggregateIsCreatedThenItSucceeds() {
        service.createStampsAggregateForClient(1);
    }

    @Test
    public void whenStampIsAddedThenItSucceeds() {
        service.createStampsAggregateForClient(1);
        service.addStamp(1, 2);
    }

    @Test
    public void whenStampIsAddedThenItReturnsCorrectAmountOfStamps() {
        service.createStampsAggregateForClient(1);
        service.addStamp(1, 2);

        MatcherAssert.assertThat(service.getNumberOfStamps(1, 2), equalTo(1));
    }

    @Test
    public void whenStampIsNotAddedThenItReturnsZeroAmountOfStamps() {
        service.createStampsAggregateForClient(1);

        MatcherAssert.assertThat(service.getNumberOfStamps(1, 2), equalTo(0));
    }

    @Test
    public void whenTwoBusinessesAreCreatedThenTheyHoldTheirStamps() {
        service.createStampsAggregateForClient(1);
        service.addStamp(1, 2);
        service.addStamp(1, 2);
        service.addStamp(1, 3);

        MatcherAssert.assertThat(service.getNumberOfStamps(1, 2), equalTo(2));
        MatcherAssert.assertThat(service.getNumberOfStamps(1, 3), equalTo(1));

    }
}
