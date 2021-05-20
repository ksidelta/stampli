package com.example.domain.context.authentication.user.repository.create;

import java.util.List;

public class UserCreationDto {
    protected String email;
    protected List<String> roles;

    public UserCreationDto() {
    }

    public UserCreationDto(String email, List<String> roles) {
        this.email = email;
        this.roles = roles;
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
}
