package com.example.infrastructure.db;

import com.example.infrastructure.db.hibernate.HibernateConfiguration;
import com.example.infrastructure.db.jdbc.JdbcConfiguration;
import com.example.infrastructure.db.flyway.FlywayConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import({FlywayConfiguration.class, HibernateConfiguration.class, JdbcConfiguration.class})
@EnableTransactionManagement
public class DatabaseConfiguration {
}
