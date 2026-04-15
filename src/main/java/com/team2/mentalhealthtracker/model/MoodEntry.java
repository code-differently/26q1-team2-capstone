package com.team2.mentalhealthtracker.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "mood_entries")
public class MoodEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer moodScore;

    @Column(nullable = false)
    private Integer anxietyLevel;

    @Column(nullable = false)
    private Integer stressLevel;

    @Column(nullable = false)
    private Integer sleepQuality;

    @Column(length = 1000)
    private String journalNote;

    @Column(nullable = false)
    private LocalDate checkInDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public MoodEntry() {
        this.checkInDate = LocalDate.now();
    }

    public MoodEntry(Integer moodScore, Integer anxietyLevel, Integer stressLevel,
                     Integer sleepQuality, String journalNote, User user) {
        this.moodScore = moodScore;
        this.anxietyLevel = anxietyLevel;
        this.stressLevel = stressLevel;
        this.sleepQuality = sleepQuality;
        this.journalNote = journalNote;
        this.user = user;
        this.checkInDate = LocalDate.now();
    }

    @PrePersist
    public void prePersist() {
        if (checkInDate == null) {
            checkInDate = LocalDate.now();
        }
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

    public Integer getAnxietyLevel() {
        return anxietyLevel;
    }

    public void setAnxietyLevel(Integer anxietyLevel) {
        this.anxietyLevel = anxietyLevel;
    }

    public Integer getStressLevel() {
        return stressLevel;
    }

    public void setStressLevel(Integer stressLevel) {
        this.stressLevel = stressLevel;
    }

    public Integer getSleepQuality() {
        return sleepQuality;
    }

    public void setSleepQuality(Integer sleepQuality) {
        this.sleepQuality = sleepQuality;
    }

    public String getJournalNote() {
        return journalNote;
    }

    public void setJournalNote(String journalNote) {
        this.journalNote = journalNote;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}