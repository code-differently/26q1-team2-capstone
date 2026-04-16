package com.team2.mentalhealthtracker.model;

import jakarta.persistence.*;

@Entity
public class CopingStrategy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String strategyName;

    private String description;

    @ManyToOne
    @JoinColumn(name = "mood_entry_id")
    private MoodEntry moodEntry;

    public CopingStrategy() {
    }

    public Long getId() {
        return id;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MoodEntry getMoodEntry() {
        return moodEntry;
    }

    public void setMoodEntry(MoodEntry moodEntry) {
        this.moodEntry = moodEntry;
    }
}