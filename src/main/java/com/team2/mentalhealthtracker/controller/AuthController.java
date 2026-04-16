package com.team2.mentalhealthtracker.controller;

import com.team2.mentalhealthtracker.dto.AuthRequest;
import com.team2.mentalhealthtracker.dto.AuthResponse;
import com.team2.mentalhealthtracker.dto.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        AuthResponse response = new AuthResponse(
                "Login successful",
                authRequest.getEmail()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        AuthResponse response = new AuthResponse(
                "Registration successful",
                registerRequest.getEmail()
        );

        return ResponseEntity.ok(response);
    }
}
