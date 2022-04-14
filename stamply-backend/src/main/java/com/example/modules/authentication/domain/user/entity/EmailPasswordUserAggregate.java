package com.example.modules.authentication.domain.user.entity;

import com.example.modules.authentication.domain.user.repository.create.UserCreationDto;
import com.example.modules.authentication.domain.roles.UserRoleEntity;

import javax.persistence.Entity;
import java.util.stream.Collectors;

@Entity
public class EmailPasswordUserAggregate extends AbstractUserAggregate {

    protected String login;

    protected String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static AbstractUserAggregate createUser(UserCreationDto userCreationDto) {
        var user = new EmailPasswordUserAggregate();
        user.setLogin(userCreationDto.getEmail());
        user.setPassword(userCreationDto.getPassword());
        user.setRoles(userCreationDto.getRoles().stream()
                .map(x -> new UserRoleEntity(user, x))
                .collect(Collectors.toList()));
        return user;
    }

}
