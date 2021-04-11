package com.example.domain.authentication.user.entity;

import com.example.domain.authentication.authenticator.UserPasswordAuthenticationDto;
import org.springframework.lang.Nullable;

import java.util.List;

public interface User {
    Integer getId();

    List<String> getRoles();

    @Nullable
    UserAuthenticationPassword getPassword();

    void addPasswordAuthentication(UserPasswordAuthenticationDto userPasswordAuthenticationDto);
}
