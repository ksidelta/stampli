package com.example.modules.stamps.repository;

import com.example.modules.stamps.domain.command.StampClientAggregate;
import com.example.modules.stamps.domain.command.StampClientAggregateRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public class MySQLStampClientAggregateRepository implements StampClientAggregateRepository {
    SessionFactory sessionFactory;

    public MySQLStampClientAggregateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Optional<StampClientAggregate> findByUserId(Integer id) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT s FROM StampClientAggregate s WHERE s.clientId=:clientId", StampClientAggregate.class)
                .setParameter("clientId", id)
                .uniqueResultOptional();

    }

    @Override
    @Transactional
    public void save(StampClientAggregate toSave) {
        sessionFactory.getCurrentSession().save(toSave);
    }

    @Override
    @Transactional
    public void update(StampClientAggregate toSave) {
        sessionFactory.getCurrentSession().update(toSave);
    }
}
