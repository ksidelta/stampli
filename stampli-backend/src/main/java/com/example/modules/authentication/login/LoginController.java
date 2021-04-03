package com.example.modules.authentication.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    protected final ProviderManager providerManager;

    @Autowired
    LoginController(ProviderManager authenticationManager) {
        this.providerManager = authenticationManager;
    }

    @PostMapping("/basic")
    public ResponseEntity<Object> login(@RequestBody LoginAndPasswordDto loginAndPasswordDto) {
        try {
            final var token = new UsernamePasswordAuthenticationToken(
                    loginAndPasswordDto.getUsername(),
                    loginAndPasswordDto.getPassword()
            );
            final var authentication = this.providerManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException badCredentialsException){
            return ResponseEntity.notFound().build();
        }
        // https://github.com/jwtk/jjwt
        return null;
    }

}

class LoginAndPasswordDto {
    private String username;
    private String password;

    public LoginAndPasswordDto(){}

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
