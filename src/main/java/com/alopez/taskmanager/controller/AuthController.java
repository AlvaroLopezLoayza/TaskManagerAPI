package com.alopez.taskmanager.controller;

import com.alopez.taskmanager.dto.LoginRequest;
import com.alopez.taskmanager.dto.RegisterRequest;
import com.alopez.taskmanager.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return service.login(request.getUsername(), request.getPassword());
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {
        service.register(request.getUsername(), request.getPassword());
        return "User created";
    }
}
