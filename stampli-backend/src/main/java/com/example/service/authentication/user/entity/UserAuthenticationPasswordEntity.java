package com.example.service.authentication.user.entity;

import com.example.domain.authentication.user.entity.UserAuthenticationPassword;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_authentication_password")
public class UserAuthenticationPasswordEntity implements UserAuthenticationPassword, Serializable {
    @Id
    @Column(name = "userId")
    protected Integer userId;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId("userId")
    protected UserEntity user;

    protected String password;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
        this.userId = user.getId();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
