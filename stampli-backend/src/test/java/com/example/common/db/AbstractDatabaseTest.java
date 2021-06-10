package com.example.common.db;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.logging.Logger;

@Transactional
public abstract class AbstractDatabaseTest {
    static final Logger logger = Logger.getLogger(AbstractDatabaseTest.class.getCanonicalName());

    @Autowired
    SessionFactory sessionFactory;

    @AfterEach
    public void flushAndRollback() {
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().getTransaction().rollback();
    }
}
