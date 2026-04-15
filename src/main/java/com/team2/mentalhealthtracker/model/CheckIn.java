package com.team2.mentalhealthtracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "checkins")
public class CheckIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Mood score is required")
    @Min(value = 1, message = "Mood score must be between 1 and 10")
    @Max(value = 10, message = "Mood score must be between 1 and 10")
    @Column(name = "mood_score", nullable = false)
    private Integer moodScore;

    @NotNull(message = "Stress level is required")
    @Min(value = 1, message = "Stress level must be between 1 and 10")
    @Max(value = 10, message = "Stress level must be between 1 and 10")
    @Column(name = "stress_level", nullable = false)
    private Integer stressLevel;

    @NotNull(message = "Sleep quality is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "sleep_quality", nullable = false)
    private SleepQuality sleepQuality;

    @Column(name = "journal_notes", columnDefinition = "TEXT")
    private String journalNotes;

    @Column(name = "triggers", columnDefinition = "TEXT")
    private String triggers;

    @Column(name = "coping_strategies_used", columnDefinition = "TEXT")
    private String copingStrategiesUsed;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public CheckIn() {
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
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

    public SleepQuality getSleepQuality() {
        return sleepQuality;
    }

    public void setSleepQuality(SleepQuality sleepQuality) {
        this.sleepQuality = sleepQuality;
    }

    public String getJournalNotes() {
        return journalNotes;
    }

    public void setJournalNotes(String journalNotes) {
        this.journalNotes = journalNotes;
    }

    public String getTriggers() {
        return triggers;
    }

    public void setTriggers(String triggers) {
        this.triggers = triggers;
    }

    public String getCopingStrategiesUsed() {
        return copingStrategiesUsed;
    }

    public void setCopingStrategiesUsed(String copingStrategiesUsed) {
        this.copingStrategiesUsed = copingStrategiesUsed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}