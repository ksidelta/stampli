package com.example.domain.authentication;

import com.example.domain.authentication.token.TokenGenerator;
import com.example.domain.authentication.token.TokenGeneratorImpl;
import com.example.domain.authentication.token.sign.AlgorithmHolder;
import com.example.domain.authentication.token.sign.InMemoryAlgorithmHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

@Configuration
public class AuthenticationConfiguration {

    @Bean
    public ProviderManager providerManager(List<AuthenticationProvider> authenticationProviders) {
        return new ProviderManager(authenticationProviders);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        final var provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        final var passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return passwordEncoder;
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        final var inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        final var user = User.builder()
                .username("username")
                .password("{noop}password")
                .authorities("USER")
                .build();

        inMemoryUserDetailsManager.createUser(user);

        return inMemoryUserDetailsManager;
    }

    @Bean
    public AlgorithmHolder algorithmHolder() {
        return new InMemoryAlgorithmHolder("TWOJASTARA");
    }

    @Bean
    public TokenGenerator tokenGenerator(AlgorithmHolder algorithmHolder) {
        return new TokenGeneratorImpl(algorithmHolder);
    }
}
