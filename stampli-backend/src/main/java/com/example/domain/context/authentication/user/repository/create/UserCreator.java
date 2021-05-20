package com.example.domain.context.authentication.user.repository.create;

import com.example.domain.context.authentication.user.entity.UserEntity;

public interface UserCreator {
    void saveUser(UserEntity user) throws UserDuplicationException;
}
