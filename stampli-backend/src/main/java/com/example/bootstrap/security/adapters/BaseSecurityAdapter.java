package com.example.bootstrap.security.adapters;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.HeaderWriterFilter;
import org.springframework.web.filter.ForwardedHeaderFilter;

import static org.springframework.security.config.Customizer.withDefaults;

public class BaseSecurityAdapter extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final HttpSecurity output = http
                .addFilterBefore(new ForwardedHeaderFilter(), HeaderWriterFilter.class)
                .formLogin(login -> login.loginPage("/login"))
                .oauth2Login(withDefaults());
        super.configure(output);
    }
}