package com.example.domain.authentication.user.entity;

import com.example.domain.authentication.authenticator.UserPasswordAuthenticationDto;
import com.example.domain.authentication.user.repository.create.UserCreationDto;
import com.example.service.authentication.roles.UserRoleEntity;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class UserEntity {
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

    public Integer getId() {
        return id;
    }

    public List<String> getRoles() {
        return roles.stream()
                .map(UserRoleEntity::getName)
                .collect(Collectors.toList());
    }

    public UserAuthenticationPasswordEntity getPassword() {
        return password;
    }

    public void addPasswordAuthentication(UserPasswordAuthenticationDto userPasswordAuthenticationDto) {
        if (password != null) {
            throw new IllegalStateException("You can add PasswordAuthentication only once");
        }

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

    public static UserEntity createUser(UserCreationDto userCreationDto) {
        var user = new UserEntity();
        user.setEmail(userCreationDto.getEmail());
        user.setRoles(userCreationDto.getRoles()
                .stream().map(x -> new UserRoleEntity(user, x)).collect(Collectors.toList()));
        return user;
    }
}