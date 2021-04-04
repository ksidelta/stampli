package com.example.controller.authentication.login;

import com.example.modules.authentication.user.UserImpl;
import com.example.service.authentication.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    protected final ProviderManager providerManager;
    protected final LoginService loginService;

    @Autowired
    LoginController(ProviderManager authenticationManager, LoginService loginService) {
        this.providerManager = authenticationManager;
        this.loginService = loginService;
    }

    @PostMapping("/basic")
    public ResponseEntity<Object> login(@RequestBody LoginAndPasswordDto loginAndPasswordDto) {
        try {
            final var token = loginService.login(loginAndPasswordDto.getUsername(), loginAndPasswordDto.getPassword());
            return ResponseEntity.ok().header("Set-Token", token).build();
        } catch (BadCredentialsException badCredentialsException) {
            return ResponseEntity.notFound().build();
        }
    }
}

class LoginAndPasswordDto {
    private String username;
    private String password;

    public LoginAndPasswordDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
