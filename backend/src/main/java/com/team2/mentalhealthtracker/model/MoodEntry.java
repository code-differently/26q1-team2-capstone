package com.team2.mentalhealthtracker.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "check_ins") // matches your database table
public class MoodEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 👇 Link to User
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Integer moodScore;
    private Integer stressLevel;

    private String sleepQuality;

    @Column(length = 2000)
    private String journalNotes;

    @Column(length = 1000)
    private String triggers;

    @Column(length = 1000)
    private String copingStrategiesUsed;

    private LocalDateTime createdAt;

    // 👇 Automatically set timestamp
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // ===== Constructors =====

    public MoodEntry() {}

    public MoodEntry(
            User user,
            Integer moodScore,
            Integer stressLevel,
            String sleepQuality,
            String journalNotes,
            String triggers,
            String copingStrategiesUsed
    ) {
        this.user = user;
        this.moodScore = moodScore;
        this.stressLevel = stressLevel;
        this.sleepQuality = sleepQuality;
        this.journalNotes = journalNotes;
        this.triggers = triggers;
        this.copingStrategiesUsed = copingStrategiesUsed;
    }

    // ===== Getters =====

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
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

    // ===== Setters =====

    public void setUser(User user) {
        this.user = user;
    }

    public void setMoodScore(Integer moodScore) {
        this.moodScore = moodScore;
    }

    public void setStressLevel(Integer stressLevel) {
        this.stressLevel = stressLevel;
    }

    public void setSleepQuality(String sleepQuality) {
        this.sleepQuality = sleepQuality;
    }

    public void setJournalNotes(String journalNotes) {
        this.journalNotes = journalNotes;
    }

    public void setTriggers(String triggers) {
        this.triggers = triggers;
    }

    public void setCopingStrategiesUsed(String copingStrategiesUsed) {
        this.copingStrategiesUsed = copingStrategiesUsed;
    }
}