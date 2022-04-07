package com.example.modules.offers.repository;

import com.example.modules.offers.domain.BusinessOfferAggregate;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class JpaOfferRepository implements OfferRepository {
    SessionFactory sessionFactory;

    public JpaOfferRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public BusinessOfferAggregate findById(Integer businessId) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT e FROM BusinessOfferAggregate e WHERE e.businessId=:id", BusinessOfferAggregate.class)
                .setParameter("id", businessId)
                .uniqueResult();
    }

    @Override
    @Transactional
    public void save(BusinessOfferAggregate businessOfferAggregate) {
        sessionFactory.getCurrentSession().save(businessOfferAggregate);
    }
}
