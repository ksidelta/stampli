package com.example.modules.authentication.service.login;

import org.springframework.security.authentication.BadCredentialsException;

public interface LoginService {
    String login(String email, String password) throws BadCredentialsException;

    String loginOrRegister(String issuer, String id);
}
