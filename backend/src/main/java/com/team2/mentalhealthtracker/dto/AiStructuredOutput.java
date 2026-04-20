package com.team2.mentalhealthtracker.dto;

public class AiStructuredOutput {
    private String suggestion;
    private String goal;

    public AiStructuredOutput() {
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }
}