package com.team2.mentalhealthtracker.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DtoCoverageTest {

    @Test
    void shouldTestLoginRequest() {
        LoginRequest request = new LoginRequest();
        request.setEmail("test@example.com");
        request.setPassword("123456");

        assertEquals("test@example.com", request.getEmail());
        assertEquals("123456", request.getPassword());
    }

    @Test
    void shouldTestRegisterRequest() {
        RegisterRequest request = new RegisterRequest();
        request.setFirstName("Meshiek");
        request.setLastName("Brown");
        request.setEmail("meshiek@example.com");
        request.setPassword("123456");

        assertEquals("Meshiek", request.getFirstName());
        assertEquals("Brown", request.getLastName());
        assertEquals("meshiek@example.com", request.getEmail());
        assertEquals("123456", request.getPassword());
    }

    @Test
    void shouldTestGoalUpdateRequest() {
        GoalUpdateRequest request = new GoalUpdateRequest();
        request.setGoal("Stay consistent");

        assertEquals("Stay consistent", request.getGoal());
    }

    @Test
    void shouldTestAiRecommendationResponse() {
        AiRecommendationResponse response =
                new AiRecommendationResponse("Take a walk", "Sleep 8 hours");

        assertEquals("Take a walk", response.getSuggestion());
        assertEquals("Sleep 8 hours", response.getSuggestedGoal());
    }

    @Test
    void shouldTestResourceResponse() {
        ResourceResponse response =
                new ResourceResponse("Help is available", List.of("a", "b"));

        assertEquals("Help is available", response.getMessage());
        assertEquals(2, response.getResourceLinks().size());
    }

    @Test
    void shouldTestMoodEntryResponse() {
        LocalDateTime now = LocalDateTime.now();

        MoodEntryResponse response = new MoodEntryResponse(
                1L, 7, 4, "GOOD", "Okay day", "school", "journaling", now
        );

        assertEquals(1L, response.getId());
        assertEquals(7, response.getMoodScore());
        assertEquals(4, response.getStressLevel());
        assertEquals("GOOD", response.getSleepQuality());
        assertEquals("Okay day", response.getJournalNotes());
        assertEquals("school", response.getTriggers());
        assertEquals("journaling", response.getCopingStrategiesUsed());
        assertEquals(now, response.getCreatedAt());
    }



}