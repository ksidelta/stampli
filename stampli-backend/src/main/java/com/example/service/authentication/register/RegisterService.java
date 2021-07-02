package com.example.service.authentication.register;

public interface RegisterService {
    Integer registerUser(String email, String password);

    void registerUser(String issuer, Integer id);
}
