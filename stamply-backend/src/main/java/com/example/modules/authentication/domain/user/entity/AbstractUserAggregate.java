package com.example.modules.authentication.domain.user.entity;

import com.example.infrastructure.domain.events.AbstractEventedAggregate;
import com.example.modules.authentication.domain.roles.UserRoleEntity;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class AbstractUserAggregate extends AbstractEventedAggregate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "id.user")
    List<UserRoleEntity> roles;

    public Integer getId() {
        return id;
    }

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
