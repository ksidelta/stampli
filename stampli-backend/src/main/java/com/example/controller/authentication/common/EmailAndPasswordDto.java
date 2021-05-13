package com.example.controller.authentication.common;

public class EmailAndPasswordDto {
    private String email;
    private String password;

    public EmailAndPasswordDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
