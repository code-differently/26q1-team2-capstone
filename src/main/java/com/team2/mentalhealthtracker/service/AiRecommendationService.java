package com.team2.mentalhealthtracker.service;

import com.team2.mentalhealthtracker.service.ThirdPartyAiClient;
import org.springframework.stereotype.Service;

@Service
public class AiRecommendationService {
    private final ThirdPartyAiClient thirdPartyAiClient;

    public AiRecommendationService(
            ThirdPartyAiClient thirdPartyAiClient){
        this.thirdPartyAiClient = thirdPartyAiClient;
    }

    public String generateSuggestion(Long userId){
        return thirdPartyAiClient.getRecommendation(
                "Generate recommendation for user " + userId
        );
    }
}
