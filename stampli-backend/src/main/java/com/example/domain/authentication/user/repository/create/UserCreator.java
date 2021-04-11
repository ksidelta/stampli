package com.example.domain.authentication.user.repository.create;

import com.example.domain.authentication.user.entity.UserEntity;

public interface UserCreator {
    void saveUser(UserEntity user) throws UserDuplicationException;
}
