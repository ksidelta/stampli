package com.example.domain.context.authentication.user.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_authentication_password")
public class UserAuthenticationPasswordEntity implements Serializable {
    @OneToOne(fetch = FetchType.EAGER)
    @Id
    @JoinColumn(name = "userId")
    protected UserAggregate user;

    protected String password;

    public UserAggregate getUser() {
        return user;
    }

    public void setUser(UserAggregate user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
