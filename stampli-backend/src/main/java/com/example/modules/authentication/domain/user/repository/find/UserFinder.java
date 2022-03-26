package com.example.modules.authentication.domain.user.repository.find;

import com.example.modules.authentication.domain.user.entity.AbstractUserAggregate;
import org.springframework.security.authentication.BadCredentialsException;

public interface UserFinder {
    AbstractUserAggregate findByUsernameAndPassword(String username, String password) throws BadCredentialsException;

    AbstractUserAggregate findByIssuerAndUid(String issuer, String uid) throws BadCredentialsException;
}
