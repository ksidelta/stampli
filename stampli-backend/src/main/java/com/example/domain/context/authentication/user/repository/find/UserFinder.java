package com.example.domain.context.authentication.user.repository.find;

import com.example.domain.context.authentication.user.entity.AbstractUserAggregate;
import org.springframework.security.authentication.BadCredentialsException;

public interface UserFinder {
    AbstractUserAggregate findByUsernameAndPassword(String username, String password) throws BadCredentialsException;
    AbstractUserAggregate findByIssuerAndUid(String issuer, Integer uid) throws BadCredentialsException;
}
