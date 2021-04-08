package com.example.service.authentication.login.user;

import com.example.domain.authentication.user.UserAuthenticationPassword;

import javax.persistence.*;

@Entity
@Table(name = "user_authentication_password")
public class UserAuthenticationPasswordEntity implements UserAuthenticationPassword {
    @Id
    protected Integer userId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    protected UserEntity user;

    protected String password;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
