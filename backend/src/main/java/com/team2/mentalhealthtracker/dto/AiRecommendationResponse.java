package com.team2.mentalhealthtracker.dto;

public class AiRecommendationResponse {

    private String title;
    private String suggestion;

    public AiRecommendationResponse() {
    }

    public AiRecommendationResponse(String title, String suggestion) {
        this.title = title;
        this.suggestion = suggestion;
    }

    public String getTitle() {
        return title;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
}