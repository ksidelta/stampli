package com.example.domain.authentication.user.repository.find;

import com.example.domain.authentication.user.entity.UserEntity;
import org.springframework.security.authentication.BadCredentialsException;

public interface UserFinder {
    UserEntity findByUsernameAndPassword(String username, String password) throws BadCredentialsException;
}
