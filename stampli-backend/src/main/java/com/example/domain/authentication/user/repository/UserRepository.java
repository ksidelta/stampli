package com.example.domain.authentication.user.repository;

import com.example.domain.authentication.user.repository.create.UserCreator;
import com.example.domain.authentication.user.repository.find.UserFinder;

public interface UserRepository extends UserFinder, UserCreator {
}
