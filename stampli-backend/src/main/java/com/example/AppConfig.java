package com.example;

import com.example.bootstrap.security.WebSecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("com.example.modules")
@Import(WebSecurityConfig.class)
public class AppConfig {
}
