package com.team2.mentalhealthtracker.controller;

import com.team2.mentalhealthtracker.dto.LoginRequest;
import com.team2.mentalhealthtracker.dto.LoginResponse;
import com.team2.mentalhealthtracker.dto.RegisterRequest;
import com.team2.mentalhealthtracker.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request.getEmail(), request.getPassword());
    }

    @PostMapping("/register")
    public LoginResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }
}