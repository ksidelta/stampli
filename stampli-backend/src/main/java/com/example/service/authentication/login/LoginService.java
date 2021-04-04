package com.example.service.authentication.login;

import org.springframework.security.authentication.BadCredentialsException;

public interface LoginService {
    String login(String username, String password) throws BadCredentialsException;
}
