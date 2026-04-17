package com.team2.mentalhealthtracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team2.mentalhealthtracker.dto.MoodEntryRequest;
import com.team2.mentalhealthtracker.dto.MoodEntryResponse;
import com.team2.mentalhealthtracker.service.CheckInService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CheckInController.class)
class CheckInControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CheckInService checkInService;

    @Test
    void shouldCreateCheckIn() throws Exception {
        Long userId = 1L;

        MoodEntryRequest request = new MoodEntryRequest();
        request.setMoodScore(7);
        request.setStressLevel(4);
        request.setSleepQuality("GOOD");
        request.setJournalNotes("Feeling okay today");
        request.setTriggers("school");
        request.setCopingStrategiesUsed("journaling");

        MoodEntryResponse response = new MoodEntryResponse(
                1L,
                7,
                4,
                "GOOD",
                "Feeling okay today",
                "school",
                "journaling",
                LocalDateTime.now()
        );

        when(checkInService.createCheckIn(userId, request)).thenReturn(response);

        mockMvc.perform(post("/api/checkins/{userId}", userId)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.moodScore").value(7))
                .andExpect(jsonPath("$.stressLevel").value(4))
                .andExpect(jsonPath("$.sleepQuality").value("GOOD"))
                .andExpect(jsonPath("$.journalNotes").value("Feeling okay today"))
                .andExpect(jsonPath("$.triggers").value("school"))
                .andExpect(jsonPath("$.copingStrategiesUsed").value("journaling"));
    }

    @Test
    void shouldGetCheckInsByUser() throws Exception {
        Long userId = 1L;

        MoodEntryResponse response = new MoodEntryResponse(
                1L,
                8,
                3,
                "OKAY",
                "Pretty good day",
                "work",
                "walking",
                LocalDateTime.now()
        );

        when(checkInService.getCheckInsByUser(userId)).thenReturn(List.of(response));

        mockMvc.perform(get("/api/checkins/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].moodScore").value(8))
                .andExpect(jsonPath("$[0].stressLevel").value(3))
                .andExpect(jsonPath("$[0].sleepQuality").value("OKAY"))
                .andExpect(jsonPath("$[0].journalNotes").value("Pretty good day"))
                .andExpect(jsonPath("$[0].triggers").value("work"))
                .andExpect(jsonPath("$[0].copingStrategiesUsed").value("walking"));
    }
}