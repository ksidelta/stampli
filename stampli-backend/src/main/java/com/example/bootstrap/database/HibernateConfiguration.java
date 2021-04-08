package com.example.bootstrap.database;

import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

public class HibernateConfiguration {
    @Bean
    @DependsOn("flyway")
    public SessionFactory sessionFactory(
            @SuppressWarnings("unused") Flyway flyway,
            DataSource dataSource) {

        return new LocalSessionFactoryBuilder(dataSource)
                .scanPackages("com.example")
                .buildSessionFactory();
    }

    @Bean
    public TransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
