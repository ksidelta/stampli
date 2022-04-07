package com.example.service.business.repository;

import com.example.BaseTestConfiguration;
import com.example.common.db.AbstractDatabaseTest;
import com.example.modules.business.domain.BusinessAggregate;
import com.example.modules.business.repository.BusinessRepository;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig({BaseTestConfiguration.class})
public class MySqlBusinessRepositoryIntegration extends AbstractDatabaseTest {
    @Autowired
    BusinessRepository mySqlBusinessRepository;

    @Autowired
    protected SessionFactory sessionFactory;

    @Test
    public void whenBusinessAggregateIsSavedThenItSucceeds() {
        final var aggregate = BusinessAggregate.createBusinessAggregate(new BusinessAggregate.Owner(1));

        mySqlBusinessRepository.save(aggregate);
    }

    @Test
    public void whenBusinessAggregateIsSavedTwiceWithSameOwnerThenItFails() {
        final var aggregate = BusinessAggregate.createBusinessAggregate(new BusinessAggregate.Owner(1));
        final var anotherAggregate = BusinessAggregate.createBusinessAggregate(new BusinessAggregate.Owner(1));

        mySqlBusinessRepository.save(aggregate);
        assertThrows(BusinessRepository.DuplicatedOwnerException.class, () -> mySqlBusinessRepository.save(anotherAggregate));
    }

    @Test
    public void givenSavedBusinessWhenFoundByIdThenReturned() {
        final var aggregate = BusinessAggregate.createBusinessAggregate(new BusinessAggregate.Owner(1));

        mySqlBusinessRepository.save(aggregate);

        final var entity = mySqlBusinessRepository.findById(aggregate.getId());
        assertThat(entity, equalTo(aggregate));
    }

    @Test
    public void givenSavedBusinessWhenRemovedThenMissing() {
        final var aggregate = BusinessAggregate.createBusinessAggregate(new BusinessAggregate.Owner(1));
        mySqlBusinessRepository.save(aggregate);

        mySqlBusinessRepository.remove(aggregate);

        assertThat(mySqlBusinessRepository.findById(aggregate.getId()), equalTo(null));
    }

    @Test
    public void givenSavedBusinessWhenFoundByIdThenDefaultImageIsReadable() {
        final var aggregate = BusinessAggregate.createBusinessAggregate(new BusinessAggregate.Owner(1));

        mySqlBusinessRepository.save(aggregate);

        final var entity = mySqlBusinessRepository.findById(aggregate.getId());

        assertThat(entity.usingBusinessProfile().getBusinessLogo().getImage().getHeight(), equalTo(512));
        assertThat(entity.usingBusinessProfile().getBusinessLogo().getImage().getHeight(), equalTo(512));
    }


    static BusinessAggregate createSampleAggregate() {
        final var businessAggregate = BusinessAggregate.createBusinessAggregate(new BusinessAggregate.Owner(1));
        return businessAggregate;
    }
}
