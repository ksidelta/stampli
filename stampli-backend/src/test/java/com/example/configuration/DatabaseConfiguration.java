package com.example.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {
    @Primary
    public DataSource dataSource() {
        final var source = new DriverManagerDataSource();

        source.setUrl("jdbc:h2:mem:testdb");
        source.setDriverClassName(org.h2.Driver.class.getName());
        source.setUsername("sa");
        source.setPassword("sa");

        return source;
    }
}
