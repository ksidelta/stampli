package com.example.modules.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/test")
public class TestController {

    @GetMapping(path = "/health")
    public String health() {
        return "ok";
    }

    @GetMapping(path = "/forbidden")
    public String forbidden() {
        return "ok";
    }
}
