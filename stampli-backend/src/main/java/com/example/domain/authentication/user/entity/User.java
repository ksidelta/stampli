package com.example.domain.authentication.user.entity;

import com.example.domain.authentication.authenticator.UserPasswordAuthenticationDto;

import java.util.List;

public interface User {
    Integer getId();

    List<String> getRoles();

    void addPasswordAuthentication(UserPasswordAuthenticationDto userPasswordAuthenticationDto);
}
