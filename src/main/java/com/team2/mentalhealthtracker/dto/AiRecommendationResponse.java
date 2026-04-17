package com.team2.mentalhealthtracker.dto;

public class AiRecommendationResponse {
    private String suggestion;
    private String suggestedGoal;

    public AiRecommendationResponse() {
    }

    public AiRecommendationResponse(String suggestion, String suggestedGoal) {
        this.suggestion = suggestion;
        this.suggestedGoal = suggestedGoal;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getSuggestedGoal() {
        return suggestedGoal;
    }

    public void setSuggestedGoal(String suggestedGoal) {
        this.suggestedGoal = suggestedGoal;
    }
}