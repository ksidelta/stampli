package com.example.service.business.repository;

import com.example.BaseTestConfiguration;
import com.example.domain.business.entity.business.BusinessAggregate;
import com.example.domain.business.repository.BusinessRepository;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig({BaseTestConfiguration.class})
public class MySqlBusinessRepositoryIntegration {
    @Autowired
    BusinessRepository mySqlBusinessRepository;

    @Autowired
    protected SessionFactory sessionFactory;

    @Test
    public void whenBusinessAggregateIsSavedThenItSucceeds() {
        final var aggregate = BusinessAggregate.createBusinessAggregate();
        try {
            mySqlBusinessRepository.save(aggregate);
        } finally {
            mySqlBusinessRepository.remove(aggregate);
        }
    }

    @Test
    public void givenSavedBusinessWhenFoundByIdThenReturned() {
        final var aggregate = BusinessAggregate.createBusinessAggregate();
        try {
            mySqlBusinessRepository.save(aggregate);

            final var entity = mySqlBusinessRepository.findById(aggregate.getId());
            assertThat(entity, equalTo(aggregate));
        } finally {
            mySqlBusinessRepository.remove(aggregate);
        }
    }

    @Test
    public void givenSavedBusinessWhenRemovedThenMissing() {
        final var aggregate = BusinessAggregate.createBusinessAggregate();
        mySqlBusinessRepository.save(aggregate);

        mySqlBusinessRepository.remove(aggregate);

        assertThat(mySqlBusinessRepository.findById(aggregate.getId()), equalTo(null));
    }

    @Test
    public void givenSavedBusinessWhenFoundByIdThenDefaultImageIsReadable() {
        final var aggregate = BusinessAggregate.createBusinessAggregate();
        try {
            mySqlBusinessRepository.save(aggregate);

            final var entity = mySqlBusinessRepository.findById(aggregate.getId());

            assertThat(entity.usingBusinessProfile().getBusinessLogo().getImage().getHeight(), equalTo(512));
            assertThat(entity.usingBusinessProfile().getBusinessLogo().getImage().getHeight(), equalTo(512));
        } finally {
            mySqlBusinessRepository.remove(aggregate);
        }
    }


    static BusinessAggregate createSampleAggregate() {
        final var businessAggregate = BusinessAggregate.createBusinessAggregate();
        return businessAggregate;
    }
}
