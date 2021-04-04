package com.example.modules.authentication.user;

import com.example.modules.authentication.token.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserImpl implements User {
    protected TokenGenerator tokenGenerator;

    @Autowired
    public UserImpl(TokenGenerator tokenGenerator) {
        this.tokenGenerator = tokenGenerator;
    }

    Integer id;
    List<String> roles;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String createToken() {
        return tokenGenerator.createToken(id, roles);
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public List<String> getRoles() {
        return roles;
    }
}
