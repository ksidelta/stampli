package com.example.domain.authentication.user.repository.create;

import com.example.domain.authentication.user.entity.User;

public interface UserCreator {
    User createUser(UserCreationDto userCreationDto) throws UserDuplicationException;
}
