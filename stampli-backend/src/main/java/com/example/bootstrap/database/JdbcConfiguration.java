package com.example.bootstrap.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class JdbcConfiguration {

    @Value("#{systemProperties['test.mysql.host']}")
    protected String host;

    @Value("#{systemProperties['test.mysql.db']}")
    protected String database;

    @Value("#{systemProperties['test.mysql.username']}")
    protected String username;

    @Value("#{systemProperties['test.mysql.password']}")
    protected String password;

    @Value("#{systemProperties['test.mysql.port']}")
    protected String port;

    @Primary
    @Bean
    public DataSource dataSource(StandardEnvironment env) {
        final var source = new DriverManagerDataSource();
        source.setUrl("jdbc:mariadb://" + host + ":" + port + "/" + database + "?user=" + username + "&password=" + password + "&enablePacketDebug=true");
        return source;
    }


}
