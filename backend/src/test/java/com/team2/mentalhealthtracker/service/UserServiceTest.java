package com.team2.mentalhealthtracker.service;

import com.team2.mentalhealthtracker.model.User;
import com.team2.mentalhealthtracker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnUserProfile() {
        Long userId = 1L;

        User user = new User();
        user.setFirstName("Meshiek");
        user.setLastName("Brown");
        user.setEmail("meshiek@example.com");
        user.setGoal("Stay consistent");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User result = userService.getUserProfile(userId);

        assertNotNull(result);
        assertEquals("Meshiek", result.getFirstName());
        assertEquals("Brown", result.getLastName());
        assertEquals("meshiek@example.com", result.getEmail());
        assertEquals("Stay consistent", result.getGoal());

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void shouldUpdateUserGoal() {
        Long userId = 1L;
        String newGoal = "Practice mindfulness daily";

        User user = new User();
        user.setFirstName("Meshiek");
        user.setLastName("Brown");
        user.setEmail("meshiek@example.com");
        user.setGoal("Old goal");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        User updatedUser = userService.updateGoal(userId, newGoal);

        assertNotNull(updatedUser);
        assertEquals("Practice mindfulness daily", updatedUser.getGoal());

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void shouldReturnUserByEmail() {
        String email = "meshiek@example.com";

        User user = new User();
        user.setFirstName("Meshiek");
        user.setLastName("Brown");
        user.setEmail(email);
        user.setGoal("Stay focused");

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        User result = userService.getUserByEmail(email);

        assertNotNull(result);
        assertEquals("meshiek@example.com", result.getEmail());
        assertEquals("Stay focused", result.getGoal());

        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void shouldThrowExceptionWhenUserProfileNotFound() {
        Long userId = 99L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> userService.getUserProfile(userId));

        assertEquals("User not found", exception.getMessage());

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void shouldThrowExceptionWhenUserByEmailNotFound() {
        String email = "missing@example.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> userService.getUserByEmail(email));

        assertEquals("User not found", exception.getMessage());

        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void shouldThrowWhenUpdatingGoalForMissingUser() {
        Long userId = 99L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> userService.updateGoal(userId, "New goal"));

        assertEquals("User not found", exception.getMessage());
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, never()).save(any(User.class));
    }
}