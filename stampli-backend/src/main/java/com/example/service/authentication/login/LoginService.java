package com.example.service.authentication.login;

import org.springframework.security.authentication.BadCredentialsException;

public interface LoginService {
    String login(String email, String password) throws BadCredentialsException;
    String login(String issuer, Integer id);
}
