package com.team2.mentalhealthtracker.service;

import com.team2.mentalhealthtracker.client.ThirdPartyAiClient;
import org.springframework.stereotype.Service;

@Service
public class AiRecommendationService {

    private final ThirdPartyAiClient thirdPartyAiClient;

    public AiRecommendationService(ThirdPartyAiClient thirdPartyAiClient) {
        this.thirdPartyAiClient = thirdPartyAiClient;
    }

    public String generateSuggestion() {
        String prompt = "Generate a short supportive mental wellness suggestion for a user based on a recent mood check-in.";
        return thirdPartyAiClient.getRecommendation(prompt);
    }
}