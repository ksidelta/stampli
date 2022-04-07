package com.example.modules.authentication.domain.user.entity;

import java.util.List;

public record UserTokenDto(String issuer, String uid, List<String> roles) {
}