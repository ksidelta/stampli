package com.example.domain.context.authentication.user.entity;

import com.example.domain.context.authentication.user.repository.create.UserCreationDto;
import com.example.service.authentication.roles.UserRoleEntity;

import javax.persistence.Entity;
import java.util.stream.Collectors;

@Entity
public class TokenUserAggregate extends AbstractUserAggregate {

    protected String uid;

    protected String issuer;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public static AbstractUserAggregate createUser(UserTokenDto userTokenDto) {
        var user = new TokenUserAggregate();
        user.setUid(userTokenDto.getUid());
        user.setIssuer(userTokenDto.getIssuer());
        user.setRoles(userTokenDto.getRoles().stream()
                .map(x -> new UserRoleEntity(user, x))
                .collect(Collectors.toList()));
        return user;
    }
}