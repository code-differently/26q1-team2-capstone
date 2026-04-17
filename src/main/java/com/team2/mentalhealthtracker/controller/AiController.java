package com.team2.mentalhealthtracker.controller;

import com.team2.mentalhealthtracker.dto.AiRecommendationResponse;
import com.team2.mentalhealthtracker.dto.AiRequest;
import com.team2.mentalhealthtracker.service.ThirdPartyAiClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "http://localhost:5173")
public class AiController {

    private final ThirdPartyAiClient aiClient;

    public AiController(ThirdPartyAiClient aiClient) {
        this.aiClient = aiClient;
    }

    @PostMapping("/recommendation")
    public AiRecommendationResponse getRecommendation(@RequestBody AiRequest request) {
        return aiClient.getRecommendationAndGoal(
                request.getMoodScore(),
                request.getStressLevel(),
                request.getSleepQuality(),
                request.getJournalNotes(),
                request.getCurrentGoal()
        );
    }
}