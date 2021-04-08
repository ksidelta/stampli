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

    @Value("#{systemEnvironment['test.mysql.host']}")
    protected String host;

    @Value("#{systemEnvironment['test.mysql.db']}")
    protected String database;

    @Value("#{systemEnvironment['test.mysql.username']}")
    protected String username;

    @Value("#{systemEnvironment['test.mysql.password']}")
    protected String password;

    @Value("#{systemEnvironment['test.mysql.port']}")
    protected String port;

    @Primary
    @Bean
    public DataSource dataSource(StandardEnvironment env) {
        final var source = new DriverManagerDataSource();
        source.setUrl("jdbc:mariadb://" + host + ":" + port + "/" + database + "?user=" + username + "&password=" + password + "&enablePacketDebug=true");
        return source;
    }


}
