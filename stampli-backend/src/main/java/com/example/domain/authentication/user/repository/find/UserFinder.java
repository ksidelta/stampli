package com.example.domain.authentication.user.repository.find;

import com.example.domain.authentication.user.entity.User;
import org.springframework.security.authentication.BadCredentialsException;

public interface UserFinder {
    User findByUsernameAndPassword(String username, String password) throws BadCredentialsException;
}
