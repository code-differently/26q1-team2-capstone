package com.team2.mentalhealthtracker.dto;

import java.time.LocalDateTime;

public class MoodEntryResponse {

    private Long id;
    private Integer moodScore;
    private Integer stressLevel;
    private String sleepQuality;
    private String journalNotes;
    private String triggers;
    private String copingStrategiesUsed;
    private LocalDateTime createdAt;

    public MoodEntryResponse() {
    }

    public MoodEntryResponse(
            Long id,
            Integer moodScore,
            Integer stressLevel,
            String sleepQuality,
            String journalNotes,
            String triggers,
            String copingStrategiesUsed,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.moodScore = moodScore;
        this.stressLevel = stressLevel;
        this.sleepQuality = sleepQuality;
        this.journalNotes = journalNotes;
        this.triggers = triggers;
        this.copingStrategiesUsed = copingStrategiesUsed;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Integer getMoodScore() {
        return moodScore;
    }

    public Integer getStressLevel() {
        return stressLevel;
    }

    public String getSleepQuality() {
        return sleepQuality;
    }

    public String getJournalNotes() {
        return journalNotes;
    }

    public String getTriggers() {
        return triggers;
    }

    public String getCopingStrategiesUsed() {
        return copingStrategiesUsed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}