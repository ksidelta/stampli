package com.example.domain.authentication.user.finder;

import com.example.domain.authentication.user.User;
import org.springframework.security.authentication.BadCredentialsException;

public interface UserFinder {
    User findByUsernameAndPassword(String username, String password) throws BadCredentialsException;
}
