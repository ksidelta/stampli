package com.example.modules.authentication.service.register;

public interface RegisterService {
    Integer registerUser(String email, String password);

    Integer registerUserWithToken(String issuer, String id);
}
