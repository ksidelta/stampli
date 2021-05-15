package com.example.bootstrap.security.adapters;

import com.example.infrastructure.jwt.BasicJwtAuthenticationProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import org.springframework.web.filter.ForwardedHeaderFilter;

import java.util.logging.Logger;

@Configuration
public class BaseSecurityAdapter extends WebSecurityConfigurerAdapter {
    final static Logger logger = Logger.getLogger(BaseSecurityAdapter.class.toString());

    final BasicJwtAuthenticationProvider basicJwtAuthenticationProvider;

    public BaseSecurityAdapter(BasicJwtAuthenticationProvider basicJwtAuthenticationProvider) {
        this.basicJwtAuthenticationProvider = basicJwtAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .mvcMatchers("/api/authentication/**").permitAll()
                        .mvcMatchers("/api/test/string").permitAll()
                        .mvcMatchers(HttpMethod.POST, "/api/business/**").authenticated()
                        .mvcMatchers(HttpMethod.PUT, "/api/business/**").authenticated()
                        .mvcMatchers(HttpMethod.GET, "/api/business/**").permitAll()
                        .anyRequest().denyAll()
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .authenticationProvider(basicJwtAuthenticationProvider)
                .csrf(AbstractHttpConfigurer::disable) // TODO: Fix it later
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .addFilterBefore(new ForwardedHeaderFilter(), WebAsyncManagerIntegrationFilter.class)
                .sessionManagement(AbstractHttpConfigurer::disable); // We will use JWT so fuck sessions
    }

}
