package com.example.service.authentication.register;

public interface RegisterService {
    Integer registerUser(String email, String password);

    Integer registerUserWithToken(String issuer, String id);
}
