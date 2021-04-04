package com.example.bootstrap.database;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class DatabaseConfiguration {
    @Bean(initMethod = "migrate")
    Flyway flyway(DataSource dataSource) {
        return Flyway
                .configure()
                .baselineOnMigrate(false)
                .locations("classpath:/resources/migrations")
                .dataSource(dataSource)
                .load();
    }
}
