package com.example.bootstrap.security;

import com.example.bootstrap.security.adapters.BaseSecurityAdapter;
import com.example.bootstrap.security.oauth.OAuthConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

@Configuration
@Import({/*BaseSecurityAdapter.class,*/ OAuthConfig.class})
public class WebSecurityConfig {

    @Bean
    public HttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }
}
