package com.team2.mentalhealthtracker.service;

import com.team2.mentalhealthtracker.dto.UserRequest;
import com.team2.mentalhealthtracker.dto.UserResponse;
import com.team2.mentalhealthtracker.model.User;
import com.team2.mentalhealthtracker.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse getUserById(Long id) {
        return mapToResponse(findUser(id));
    }

    public UserResponse updateUser(Long id, UserRequest request) {
        User user = findUser(id);

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        return mapToResponse(userRepository.save(user));
    }

    private User findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    private UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }
}