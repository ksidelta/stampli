package com.example.modules.authentication.domain.user.entity

data class UserTokenDto(val issuer: String, val uid: String, val roles: List<String>);