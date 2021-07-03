package com.example.domain.context.authentication.user.entity

data class UserTokenDto(val issuer: String, val uid: String, val roles: List<String>);