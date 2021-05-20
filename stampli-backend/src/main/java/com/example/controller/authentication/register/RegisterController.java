package com.example.controller.authentication.register;

import com.example.controller.authentication.common.EmailAndPasswordDto;
import com.example.domain.context.authentication.user.repository.create.UserDuplicationException;
import com.example.service.authentication.register.RegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;

@Controller
@RequestMapping("/api/authentication/register")
public class RegisterController {
    RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("basic")
    public ResponseEntity<Object> registerUser(@RequestBody EmailAndPasswordDto emailAndPasswordDto) {
        try {
            registerService.registerUser(emailAndPasswordDto.getEmail(), emailAndPasswordDto.getPassword());
        } catch (UserDuplicationException userDuplicationException) {
            return ResponseEntity.status(CONFLICT).build();
        }

        return ResponseEntity.status(CREATED).build();
    }
}
