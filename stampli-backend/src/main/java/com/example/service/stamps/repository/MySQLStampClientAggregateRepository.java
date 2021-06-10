package com.example.service.stamps.repository;

import com.example.domain.context.stamps.command.StampClientAggregate;
import com.example.domain.context.stamps.command.StampClientAggregateRepository;
import com.example.service.challenge.repository.JpaChallengeRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.logging.Logger;

@Repository
public class MySQLStampClientAggregateRepository implements StampClientAggregateRepository {
    SessionFactory sessionFactory;

    public MySQLStampClientAggregateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<StampClientAggregate> findByUserId(Integer id) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT s FROM StampClientAggregate s WHERE s.clientId=:clientId", StampClientAggregate.class)
                .setParameter("clientId", id)
                .uniqueResultOptional();

    }

    @Override
    public void save(StampClientAggregate toSave) {
        sessionFactory.getCurrentSession().save(toSave);
    }

    @Override
    public void update(StampClientAggregate toSave) {
        sessionFactory.getCurrentSession().update(toSave);
    }
}
