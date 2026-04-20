package com.team2.mentalhealthtracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team2.mentalhealthtracker.dto.MoodEntryRequest;
import com.team2.mentalhealthtracker.dto.MoodEntryResponse;
import com.team2.mentalhealthtracker.service.CheckInService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CheckInController.class)
class CheckInControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CheckInService checkInService;

    @Test
    void shouldCreateCheckIn() throws Exception {
        Long userId = 1L;

        MoodEntryRequest request = new MoodEntryRequest();
        request.setMoodScore(7);
        request.setStressLevel(4);
        request.setSleepQuality("GOOD");
        request.setJournalNotes("Feeling okay today");
        request.setTriggers("Work stress");
        request.setCopingStrategiesUsed("Meditation");

        MoodEntryResponse response = new MoodEntryResponse(
                1L,
                7,
                4,
                "GOOD",
                "Feeling okay today",
                "Work stress",
                "Meditation",
                LocalDateTime.now()
        );

        when(checkInService.createCheckIn(eq(userId), any(MoodEntryRequest.class)))
                .thenReturn(response);

        var mvcResult = mockMvc.perform(post("/api/checkins/{userId}", userId)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString();
        System.out.println("RESPONSE BODY: " + body);

        assertFalse(body.isBlank());
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