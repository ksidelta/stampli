package com.example.infrastructure.security;

import com.example.infrastructure.security.adapters.BaseSecurityAdapter;
import com.example.infrastructure.security.oauth.OAuthConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

@Configuration
@Import({BaseSecurityAdapter.class, OAuthConfig.class})
@Primary
public class WebSecurityConfig {

    @Bean
    public HttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }
}
