package com.example.service.authentication.register;

public interface RegisterService {
    Integer registerUser(String email, String password);

    Integer registerUser(String issuer, Integer id);
}
