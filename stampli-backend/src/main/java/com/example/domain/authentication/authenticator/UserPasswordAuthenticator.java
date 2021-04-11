package com.example.domain.authentication.authenticator;

import com.example.domain.authentication.user.entity.User;
import com.example.domain.authentication.user.entity.UserAuthenticationPassword;

public interface UserPasswordAuthenticator {
    void addAuthentication(User user, UserPasswordAuthenticationDto userPasswordAuthenticationDto);
}
