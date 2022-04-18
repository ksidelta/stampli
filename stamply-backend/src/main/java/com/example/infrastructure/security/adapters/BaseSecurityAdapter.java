package com.example.infrastructure.security.adapters;

import com.example.infrastructure.jwt.BasicJwtAuthenticationProvider;
import com.example.infrastructure.jwt.external.ProhibitExternalProviderFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationFilter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import org.springframework.web.filter.ForwardedHeaderFilter;

import java.util.logging.Logger;

@Configuration
public class BaseSecurityAdapter extends WebSecurityConfigurerAdapter {
    final static Logger logger = Logger.getLogger(BaseSecurityAdapter.class.toString());

    final AuthenticationManager authenticationManager;
    
    public BaseSecurityAdapter(
            BasicJwtAuthenticationProvider basicJwtAuthenticationProvider,
            @Qualifier JwtAuthenticationProvider googleProvider
    ) {
        authenticationManager = new ProviderManager(basicJwtAuthenticationProvider, googleProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .mvcMatchers("/api/authentication/**").permitAll()
                        .mvcMatchers("/api/test/string").permitAll()
                        //.mvcMatchers("/api/socket").permitAll()
                        .mvcMatchers(HttpMethod.POST, "/api/business/**").authenticated()
                        .mvcMatchers(HttpMethod.PUT, "/api/business/**").authenticated()
                        .mvcMatchers(HttpMethod.GET, "/api/business/**").permitAll()
                        .mvcMatchers(HttpMethod.GET, "/api/business/").authenticated() // it gets business for current user
                        .mvcMatchers(HttpMethod.GET, "/api/test/forbidden").denyAll() // for testing that spring boot didn't break anything
                        .anyRequest().permitAll()
                )
                .oauth2ResourceServer(x -> x.jwt().authenticationManager(authenticationManager))
                .csrf(AbstractHttpConfigurer::disable) // TODO: Fix it later
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .addFilterBefore(new ForwardedHeaderFilter(), WebAsyncManagerIntegrationFilter.class)
                .addFilterAfter(new ProhibitExternalProviderFilter(), BearerTokenAuthenticationFilter.class)
                .sessionManagement(AbstractHttpConfigurer::disable); // We will use JWT so fuck sessions
    }

}
