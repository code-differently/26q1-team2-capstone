package com.team2.mentalhealthtracker.dto;

public class AiRecommendationResponse {

    private String label;
    private String suggestion;

    public AiRecommendationResponse() {
    }

    public AiRecommendationResponse(String label, String suggestion) {
        this.label = label;
        this.suggestion = suggestion;
    }

    public String getLabel() {
        return label;
    }

    public String getSuggestion() {
        return suggestion;
    }
}