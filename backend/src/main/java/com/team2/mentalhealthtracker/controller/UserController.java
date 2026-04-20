package com.team2.mentalhealthtracker.controller;

import com.team2.mentalhealthtracker.dto.GoalUpdateRequest;
import com.team2.mentalhealthtracker.model.User;
import com.team2.mentalhealthtracker.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUserProfile(@PathVariable Long id) {
        return userService.getUserProfile(id);
    }

    @PutMapping("/{id}/goal")
    public User updateGoal(@PathVariable Long id, @RequestBody GoalUpdateRequest request) {
        return userService.updateGoal(id, request.getGoal());
    }
}