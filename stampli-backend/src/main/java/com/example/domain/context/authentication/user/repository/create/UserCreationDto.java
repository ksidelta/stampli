package com.example.domain.context.authentication.user.repository.create;

import java.util.List;

public class UserCreationDto {
    protected String email;
    protected String password;
    protected List<String> roles;

    public UserCreationDto() {
    }

    public UserCreationDto(String email, String password, List<String> roles) {
        this.email = email;
        this.roles = roles;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
