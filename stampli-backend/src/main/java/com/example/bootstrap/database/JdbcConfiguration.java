package com.example.bootstrap.database;

import com.example.infrastructure.db.jdbc.RetryableDataSource;
import org.mariadb.jdbc.Driver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class JdbcConfiguration {

    @Value("#{systemProperties['mysql.host']}")
    protected String host;

    @Value("#{systemProperties['mysql.db']}")
    protected String database;

    @Value("#{systemProperties['mysql.username']}")
    protected String username;

    @Value("#{systemProperties['mysql.password']}")
    protected String password;

    @Value("#{systemProperties['mysql.port']}")
    protected String port;

    @Primary
    @Bean
    public DataSource dataSource(StandardEnvironment env) {
        final var source = new DriverManagerDataSource();
        source.setUrl("jdbc:mariadb://" + host + ":" + port + "/" + database + "?user=" + username + "&password=" + password + "&enablePacketDebug=true");
        source.setDriverClassName(Driver.class.getName());
        return new RetryableDataSource(source);
    }


}
