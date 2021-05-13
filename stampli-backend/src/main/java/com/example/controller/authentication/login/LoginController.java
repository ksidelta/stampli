package com.example.controller.authentication.login;

import com.example.controller.authentication.common.EmailAndPasswordDto;
import com.example.service.authentication.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication/login")
public class LoginController {
    protected final LoginService loginService;

    @Autowired
    LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/basic")
    public ResponseEntity<Object> login(@RequestBody EmailAndPasswordDto emailAndPasswordDto) {
        try {
            final var token = loginService.login(emailAndPasswordDto.getEmail(), emailAndPasswordDto.getPassword());
            return ResponseEntity.ok().header("Set-Token", token).build();
        } catch (BadCredentialsException badCredentialsException) {
            return ResponseEntity.notFound().build();
        }
    }
}

