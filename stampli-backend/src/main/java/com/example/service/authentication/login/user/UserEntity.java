package com.example.service.authentication.login.user;

import com.example.domain.authentication.user.User;
import com.example.service.authentication.login.user.roles.UserRoleEntity;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class UserEntity implements User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "id.user")
    List<UserRoleEntity> roles;

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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
    }
}
