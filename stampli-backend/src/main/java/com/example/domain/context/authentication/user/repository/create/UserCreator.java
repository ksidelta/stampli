package com.example.domain.context.authentication.user.repository.create;

import com.example.domain.context.authentication.user.entity.UserAggregate;

public interface UserCreator {
    void saveUser(UserAggregate user) throws UserDuplicationException;
}
