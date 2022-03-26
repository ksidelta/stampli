package com.example.modules.authentication.domain.user.repository;

import com.example.modules.authentication.domain.user.repository.create.UserCreator;
import com.example.modules.authentication.domain.user.repository.find.UserFinder;

public interface UserRepository extends UserFinder, UserCreator {
}
