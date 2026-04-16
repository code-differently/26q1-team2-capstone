package com.team2.mentalhealthtracker.client;

import org.springframework.stereotype.Component;

@Component
public class ThirdPartyAiClient {

    public String getRecommendation(String prompt) {
        return "AI suggestion placeholder based on prompt: " + prompt;
    }
}