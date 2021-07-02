package com.example.domain.context.authentication.user.repository.create;

import com.example.domain.context.authentication.user.entity.AbstractUserAggregate;

public interface UserCreator {
    void saveUser(AbstractUserAggregate user) throws UserDuplicationException;
}
