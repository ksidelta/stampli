package com.example.bootstrap.database;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import({FlywayConfiguration.class, HibernateConfiguration.class, JdbcConfiguration.class})
@EnableTransactionManagement
public class DatabaseConfiguration {
}
