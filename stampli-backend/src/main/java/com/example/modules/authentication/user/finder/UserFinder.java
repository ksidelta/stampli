package com.example.modules.authentication.user.finder;

import com.example.modules.authentication.user.User;
import org.springframework.security.authentication.BadCredentialsException;

public interface UserFinder {
    User findByUsernameAndPassword(String username, String password) throws BadCredentialsException;
}
