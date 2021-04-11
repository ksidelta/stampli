package com.example.domain.authentication.user.authenticator;

import com.example.domain.authentication.user.User;
import com.example.domain.authentication.user.UserAuthenticationPassword;

public interface UserPasswordAuthenticator {
    UserAuthenticationPassword addAuthentication(User user, UserPasswordAuthenticationDto userPasswordAuthenticationDto);
}
