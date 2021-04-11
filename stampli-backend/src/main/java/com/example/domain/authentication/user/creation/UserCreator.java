package com.example.domain.authentication.user.creation;

import com.example.domain.authentication.user.User;

public interface UserCreator {
    User createUser(UserCreationDto userCreationDto) throws UserDuplicationException;
}
