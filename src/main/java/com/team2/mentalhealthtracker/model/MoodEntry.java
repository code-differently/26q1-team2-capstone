package com.team2.mentalhealthtracker.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MoodEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int moodScore;

    private int stressLevel;

    private String sleepQuality;

    @Column(length = 1000)
    private String journalNotes;

    private LocalDateTime createdAt;

    public MoodEntry() {
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public int getMoodScore() {
        return moodScore;
    }

    public void setMoodScore(int moodScore) {
        this.moodScore = moodScore;
    }

    public int getStressLevel() {
        return stressLevel;
    }

    public void setStressLevel(int stressLevel) {
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
}