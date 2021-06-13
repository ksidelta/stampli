package com.example.infrastructure.db;

import com.example.domain.context.stamps.command.StampClientAggregate;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class DbTestUtil {

    SessionFactory sessionFactory;

    public DbTestUtil(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void removeAll() {
        sessionFactory.getCurrentSession()
                .createQuery("SELECT e FROM StampClientAggregate e", StampClientAggregate.class)
                .getResultStream()
                .forEach(entity -> sessionFactory.getCurrentSession().delete(entity));
    }
}
