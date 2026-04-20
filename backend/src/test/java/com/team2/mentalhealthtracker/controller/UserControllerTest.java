package com.team2.mentalhealthtracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team2.mentalhealthtracker.dto.GoalUpdateRequest;
import com.team2.mentalhealthtracker.model.User;
import com.team2.mentalhealthtracker.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;

    @Test
    void shouldReturnUserProfile() throws Exception {
        Long userId = 1L;

        User user = new User();
        user.setId(userId);
        user.setFirstName("Meshiek");
        user.setLastName("Brown");
        user.setEmail("meshiek@example.com");
        user.setGoal("I want to get better");

        when(userService.getUserProfile(userId)).thenReturn(user);

        mockMvc.perform(get("/api/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Meshiek"))
                .andExpect(jsonPath("$.lastName").value("Brown"))
                .andExpect(jsonPath("$.email").value("meshiek@example.com"))
                .andExpect(jsonPath("$.goal").value("I want to get better"));
    }

    @Test
    void shouldUpdateGoal() throws Exception {
        Long userId = 1L;

        GoalUpdateRequest request = new GoalUpdateRequest();
        request.setGoal("Practice mindfulness daily");

        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setFirstName("Meshiek");
        updatedUser.setLastName("Brown");
        updatedUser.setEmail("meshiek@example.com");
        updatedUser.setGoal("Practice mindfulness daily");

        when(userService.updateGoal(eq(userId), anyString())).thenReturn(updatedUser);

        mockMvc.perform(put("/api/users/{id}/goal", userId)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Meshiek"))
                .andExpect(jsonPath("$.lastName").value("Brown"))
                .andExpect(jsonPath("$.email").value("meshiek@example.com"))
                .andExpect(jsonPath("$.goal").value("Practice mindfulness daily"));
    }
}