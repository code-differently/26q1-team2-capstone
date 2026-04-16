package com.team2.mentalhealthtracker.dto;

import java.time.LocalDateTime;

public class MoodEntryResponse {

    private Long id;
    private Integer moodScore;
    private Integer stressLevel;
    private String sleepQuality;
    private String journalNotes;
    private LocalDateTime createdAt;

    public MoodEntryResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMoodScore() {
        return moodScore;
    }

    public void setMoodScore(Integer moodScore) {
        this.moodScore = moodScore;
    }

    public Integer getStressLevel() {
        return stressLevel;
    }

    public void setStressLevel(Integer stressLevel) {
        this.stressLevel = stressLevel;
    }

    public String getSleepQuality() {
        return sleepQuality;
    }

    public void setSleepQuality(String sleepQuality) {
        this.sleepQuality = sleepQuality;
    }

    public String getJournalNotes() {
        return journalNotes;
    }

    public void setJournalNotes(String journalNotes) {
        this.journalNotes = journalNotes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
