package com.example.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Primary
    public DataSource dataSource(StandardEnvironment env) {
        final var source = new DriverManagerDataSource();

        source.setUrl("jdbc:mariadb://localhost:" + env.getRequiredProperty("test.mysql.port") + "/" + env.getRequiredProperty("test.mysql.db"));
        source.setDriverClassName(org.mariadb.jdbc.Driver.class.getName());
        source.setUsername(env.getRequiredProperty("test.mysql.username"));
        source.setPassword(env.getRequiredProperty("test.mysql.password"));

        return source;
    }
}
