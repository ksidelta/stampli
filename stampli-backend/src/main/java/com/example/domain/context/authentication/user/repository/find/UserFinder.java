package com.example.domain.context.authentication.user.repository.find;

import com.example.domain.context.authentication.user.entity.UserAggregate;
import org.springframework.security.authentication.BadCredentialsException;

public interface UserFinder {
    UserAggregate findByUsernameAndPassword(String username, String password) throws BadCredentialsException;
}
