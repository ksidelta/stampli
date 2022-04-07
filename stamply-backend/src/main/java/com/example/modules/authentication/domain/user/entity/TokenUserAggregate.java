package com.example.modules.authentication.domain.user.entity;

import com.example.modules.authentication.domain.roles.UserRoleEntity;

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
        user.setUid(userTokenDto.uid());
        user.setIssuer(userTokenDto.issuer());
        user.setRoles(userTokenDto.roles().stream()
                .map(x -> new UserRoleEntity(user, x))
                .collect(Collectors.toList()));
        return user;
    }
}
