package com.example.service.business.repository;

import com.example.domain.business.entity.business.BusinessAggregate;
import com.example.domain.business.repository.BusinessRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

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
        this.sessionFactory.getCurrentSession().save(businessAggregate);
    }

    @Override
    @Transactional
    public BusinessAggregate findById(final Integer businessId) {
        return this.sessionFactory.getCurrentSession().get(BusinessAggregate.class, businessId);
    }

    @Override
    @Transactional
    public void remove(BusinessAggregate businessAggregate) {
        this.sessionFactory.getCurrentSession().remove(businessAggregate);
    }
}
