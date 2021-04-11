package com.example.service.authentication.user.entity;

import com.example.domain.authentication.authenticator.UserPasswordAuthenticationDto;
import com.example.domain.authentication.user.entity.User;
import com.example.service.authentication.roles.UserRoleEntity;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class UserEntity implements User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    protected String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "id.user")
    List<UserRoleEntity> roles;

    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    protected UserAuthenticationPasswordEntity password;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public List<String> getRoles() {
        return roles.stream()
                .map(UserRoleEntity::getName)
                .collect(Collectors.toList());
    }

    @Override
    public void addPasswordAuthentication(UserPasswordAuthenticationDto userPasswordAuthenticationDto) {
        password = new UserAuthenticationPasswordEntity();
        password.setPassword(userPasswordAuthenticationDto.getPassword());
        password.setUser(this);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
    }
}
