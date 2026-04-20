package com.team2.mentalhealthtracker.service;

import com.team2.mentalhealthtracker.dto.LoginResponse;
import com.team2.mentalhealthtracker.dto.RegisterRequest;
import com.team2.mentalhealthtracker.model.User;
import com.team2.mentalhealthtracker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldLoginSuccessfully() {
        String email = "mesheik@example.com";
        String rawPassword = "123456";

        User user = new User();
        user.setId(1L);
        user.setFirstName("Mesheik");
        user.setLastName("Brown");
        user.setEmail(email);
        user.setPassword("encodedPassword");
        user.setGoal("I want to get better");

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(rawPassword, "encodedPassword")).thenReturn(true);

        LoginResponse response = authService.login(email, rawPassword);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Mesheik", response.getFirstName());
        assertEquals("Brown", response.getLastName());
        assertEquals(email, response.getEmail());
        assertEquals("I want to get better", response.getGoal());
        assertEquals("Login successful.", response.getMessage());

        verify(userRepository, times(1)).findByEmail(email);
        verify(passwordEncoder, times(1)).matches(rawPassword, "encodedPassword");
    }

    @Test
    void shouldThrowExceptionWhenLoginEmailNotFound() {
        String email = "missing@example.com";
        String password = "123456";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> authService.login(email, password));

        assertEquals("Invalid email or password", exception.getMessage());
        verify(userRepository, times(1)).findByEmail(email);
        verify(passwordEncoder, never()).matches(anyString(), anyString());
    }

    @Test
    void shouldThrowExceptionWhenLoginPasswordDoesNotMatch() {
        String email = "mesheik@example.com";
        String rawPassword = "wrongPassword";

        User user = new User();
        user.setEmail(email);
        user.setPassword("encodedPassword");

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(rawPassword, "encodedPassword")).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> authService.login(email, rawPassword));

        assertEquals("Invalid email or password", exception.getMessage());
        verify(userRepository, times(1)).findByEmail(email);
        verify(passwordEncoder, times(1)).matches(rawPassword, "encodedPassword");
    }

    @Test
    void shouldRegisterSuccessfully() {
        RegisterRequest request = new RegisterRequest();
        request.setFirstName("Mesheik");
        request.setLastName("Brown");
        request.setEmail("mesheik@example.com");
        request.setPassword("123456");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setFirstName("Mesheik");
        savedUser.setLastName("Brown");
        savedUser.setEmail("mesheik@example.com");
        savedUser.setPassword("encodedPassword");
        savedUser.setGoal("I want to get better");

        when(userRepository.existsByEmail("mesheik@example.com")).thenReturn(false);
        when(passwordEncoder.encode("123456")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        LoginResponse response = authService.register(request);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Mesheik", response.getFirstName());
        assertEquals("Brown", response.getLastName());
        assertEquals("mesheik@example.com", response.getEmail());
        assertEquals("I want to get better", response.getGoal());
        assertEquals("Registration successful.", response.getMessage());

        verify(userRepository, times(1)).existsByEmail("mesheik@example.com");
        verify(passwordEncoder, times(1)).encode("123456");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void shouldThrowExceptionWhenRegisterEmailIsBlank() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("");
        request.setPassword("123456");

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> authService.register(request));

        assertEquals("Email is required", exception.getMessage());
        verify(userRepository, never()).existsByEmail(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void shouldThrowExceptionWhenRegisterPasswordIsBlank() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("mesheik@example.com");
        request.setPassword("");

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> authService.register(request));

        assertEquals("Password is required", exception.getMessage());
        verify(userRepository, never()).existsByEmail(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void shouldThrowExceptionWhenRegisterEmailAlreadyExists() {
        RegisterRequest request = new RegisterRequest();
        request.setFirstName("Mesheik");
        request.setLastName("Brown");
        request.setEmail("mesheik@example.com");
        request.setPassword("123456");

        when(userRepository.existsByEmail("mesheik@example.com")).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> authService.register(request));

        assertEquals("An account with that email already exists", exception.getMessage());
        verify(userRepository, times(1)).existsByEmail("mesheik@example.com");
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void shouldThrowExceptionWhenRegisterEmailIsNull() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail(null);
        request.setPassword("123456");

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> authService.register(request));

        assertEquals("Email is required", exception.getMessage());
        verify(userRepository, never()).existsByEmail(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void shouldThrowExceptionWhenRegisterPasswordIsNull() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("meshiek@example.com");
        request.setPassword(null);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> authService.register(request));

        assertEquals("Password is required", exception.getMessage());
        verify(userRepository, never()).existsByEmail(anyString());
        verify(userRepository, never()).save(any(User.class));
    }
}