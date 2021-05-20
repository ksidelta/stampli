package com.example.domain.context.authentication.authenticator;

public class UserPasswordAuthenticationDto {
    protected String password;

    public UserPasswordAuthenticationDto() {
    }

    public UserPasswordAuthenticationDto(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
