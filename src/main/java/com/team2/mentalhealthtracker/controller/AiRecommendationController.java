package com.team2.mentalhealthtracker.controller;

import com.team2.mentalhealthtracker.dto.AiRecommendationResponse;
import com.team2.mentalhealthtracker.service.AiRecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "http://localhost:3000")
public class AiRecommendationController {

    private final AiRecommendationService aiRecommendationService;

    public AiRecommendationController(AiRecommendationService aiRecommendationService) {
        this.aiRecommendationService = aiRecommendationService;
    }

    @PostMapping("/recommendations")
    public ResponseEntity<AiRecommendationResponse> getRecommendation() {
        String suggestion = aiRecommendationService.generateSuggestion();

        AiRecommendationResponse response = new AiRecommendationResponse(
                "AI-Generated Suggestion",
                suggestion
        );

        return ResponseEntity.ok(response);
    }
}