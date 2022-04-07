package com.example.bootstrap.database;

import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

public class FlywayConfiguration {
    @Bean(initMethod = "migrate")
    Flyway flyway(DataSource dataSource) {
        return Flyway
                .configure()
                .baselineOnMigrate(true)
                .locations("classpath:/migrations")
                .dataSource(dataSource)
                .load();
    }
}
