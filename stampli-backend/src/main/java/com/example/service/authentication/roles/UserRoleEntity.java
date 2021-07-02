package com.example.service.authentication.roles;

import com.example.domain.context.authentication.user.entity.AbstractUserAggregate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
class UserAndRoleNameUniqueId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "userId")
    protected AbstractUserAggregate user;

    protected String name;

    public AbstractUserAggregate getUser() {
        return user;
    }

    public void setUser(AbstractUserAggregate user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAndRoleNameUniqueId that = (UserAndRoleNameUniqueId) o;
        return Objects.equals(user.getId(), that.user.getId()) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user.getId(), name);
    }
}

@Entity
@Table(name = "user_roles")
public class UserRoleEntity {
    @EmbeddedId
    protected UserAndRoleNameUniqueId id = new UserAndRoleNameUniqueId();

    public UserRoleEntity() {
    }

    public UserRoleEntity(AbstractUserAggregate user, String name) {
        id.setUser(user);
        id.setName(name);
    }

    public void setUser(AbstractUserAggregate user) {
        this.id.setUser(user);
    }

    public void setName(String name) {
        this.id.setName(name);
    }

    public String getName() {
        return this.id.getName();
    }
}
