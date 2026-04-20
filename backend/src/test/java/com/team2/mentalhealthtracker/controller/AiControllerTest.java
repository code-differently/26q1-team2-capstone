package com.team2.mentalhealthtracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team2.mentalhealthtracker.dto.AiRecommendationResponse;
import com.team2.mentalhealthtracker.dto.AiRequest;
import com.team2.mentalhealthtracker.service.ThirdPartyAiClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AiController.class)
class AiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ThirdPartyAiClient aiClient;

    @Test
    void shouldReturnAiRecommendation() throws Exception {
        AiRequest request = new AiRequest();
        request.setMoodScore(4);
        request.setStressLevel(7);
        request.setSleepQuality(2);
        request.setJournalNotes("I have been feeling overwhelmed lately.");
        request.setCurrentGoal("Improve my daily routine");

        AiRecommendationResponse response = new AiRecommendationResponse(
                "Try taking a short walk, drinking some water, and giving yourself 10 minutes to reset today.",
                "Spend 10 minutes tonight doing one calming activity before bed."
        );

        when(aiClient.getRecommendationAndGoal(
                anyInt(),
                anyInt(),
                anyInt(),
                anyString(),
                anyString()
        )).thenReturn(response);

        mockMvc.perform(post("/api/ai/recommendation")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.suggestion").value("Try taking a short walk, drinking some water, and giving yourself 10 minutes to reset today."))
                .andExpect(jsonPath("$.suggestedGoal").value("Spend 10 minutes tonight doing one calming activity before bed."));}}