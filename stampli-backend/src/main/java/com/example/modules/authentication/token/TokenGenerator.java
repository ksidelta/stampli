package com.example.modules.authentication.token;

import com.example.modules.authentication.roles.UserRoles;

import java.util.EnumSet;
import java.util.List;

public interface TokenGenerator {
    String createToken(Integer userId, List<String> roles);
}
