package com.example.domain.context.authentication.user.repository;

import com.example.domain.context.authentication.user.repository.create.UserCreator;
import com.example.domain.context.authentication.user.repository.find.UserFinder;

public interface UserRepository extends UserFinder, UserCreator {
}
