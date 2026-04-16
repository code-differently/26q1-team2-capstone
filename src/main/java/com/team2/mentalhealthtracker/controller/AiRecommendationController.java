package com.team2.mentalhealthtracker.controller;

import com.team2.mentalhealthtracker.dto.AiRecommendationResponse;
import com.team2.mentalhealthtracker.service.AiRecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AiRecommendationController {
    private final AiRecommendationService aiRecommendationService;

    public AiRecommendationController(
            AiRecommendationService aiRecommendationService){
        this.aiRecommendationService = aiRecommendationService;
    }

    @PostMapping("/recommendations/{userId}")
    public ResponseEntity<AiRecommendationResponse> getRecommendation(
            @PathVariable Long userId){
        return ResponseEntity.ok(
                new AiRecommendationResponse(
                        "AI-Generated Suggestion",
                        aiRecommendationService.generateSuggestion(userId)
                ));
    }
}