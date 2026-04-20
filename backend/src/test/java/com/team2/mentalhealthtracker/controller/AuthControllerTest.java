package com.team2.mentalhealthtracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team2.mentalhealthtracker.dto.LoginRequest;
import com.team2.mentalhealthtracker.dto.LoginResponse;
import com.team2.mentalhealthtracker.dto.RegisterRequest;
import com.team2.mentalhealthtracker.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AuthService authService;

    @Test
    void shouldLoginSuccessfully() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setEmail("meshiek@example.com");
        request.setPassword("123456");

        LoginResponse response = new LoginResponse(
                1L,
                "Meshiek",
                "Brown",
                "meshiek@example.com",
                "I want to get better",
                "Login successful."
        );

        when(authService.login(eq("meshiek@example.com"), eq("123456"))).thenReturn(response);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Meshiek"))
                .andExpect(jsonPath("$.lastName").value("Brown"))
                .andExpect(jsonPath("$.email").value("meshiek@example.com"))
                .andExpect(jsonPath("$.goal").value("I want to get better"))
                .andExpect(jsonPath("$.message").value("Login successful."));
    }

    @Test
    void shouldRegisterSuccessfully() throws Exception {
        RegisterRequest request = new RegisterRequest();
        request.setFirstName("Meshiek");
        request.setLastName("Brown");
        request.setEmail("meshiek@example.com");
        request.setPassword("123456");

        LoginResponse response = new LoginResponse(
                1L,
                "Meshiek",
                "Brown",
                "meshiek@example.com",
                "I want to get better",
                "Registration successful."
        );

        when(authService.register(any(RegisterRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Meshiek"))
                .andExpect(jsonPath("$.lastName").value("Brown"))
                .andExpect(jsonPath("$.email").value("meshiek@example.com"))
                .andExpect(jsonPath("$.goal").value("I want to get better"))
                .andExpect(jsonPath("$.message").value("Registration successful."));
    }
}