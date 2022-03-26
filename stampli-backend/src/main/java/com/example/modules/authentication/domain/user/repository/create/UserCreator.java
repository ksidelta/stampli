package com.example.modules.authentication.domain.user.repository.create;

import com.example.modules.authentication.domain.user.entity.AbstractUserAggregate;

public interface UserCreator {
    void saveUser(AbstractUserAggregate user) throws UserDuplicationException;
}
