package com.example.service.business.repository;

import com.example.domain.business.entity.business.BusinessAggregate;
import com.example.domain.business.repository.BusinessRepository;
import com.example.domain.business.repository.DuplicatedOwnerException;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public class MySqlBusinessRepository implements BusinessRepository {
    SessionFactory sessionFactory;

    public MySqlBusinessRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void save(BusinessAggregate businessAggregate) {
        try {
            this.sessionFactory.getCurrentSession().save(businessAggregate);
        } catch (ConstraintViolationException exception) {
            throw new DuplicatedOwnerException();
        }
    }

    @Override
    @Transactional
    public BusinessAggregate findById(final Integer businessId) {
        return this.sessionFactory.getCurrentSession().get(BusinessAggregate.class, businessId);
    }

    @Override
    public Optional<BusinessAggregate> findByOwnerId(Integer ownerId) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM BusinessAggregate b WHERE b.owner.ownerId = :ownerId", BusinessAggregate.class)
                .setParameter("ownerId", ownerId)
                .uniqueResultOptional();
    }

    @Override
    @Transactional
    public void remove(BusinessAggregate business) {
        this.sessionFactory.getCurrentSession().remove(business);
    }
}
