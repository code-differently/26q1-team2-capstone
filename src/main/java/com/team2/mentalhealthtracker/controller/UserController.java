package com.team2.mentalhealthtracker.controller;

import com.team2.mentalhealthtracker.dto.UserRequest;
import com.team2.mentalhealthtracker.dto.UserResponse;
import com.team2.mentalhealthtracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequest request){
        return ResponseEntity.ok(userService.updateUser(id, request));
    }
}