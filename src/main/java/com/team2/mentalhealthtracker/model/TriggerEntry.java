package com.team2.mentalhealthtracker.model;

import jakarta.persistence.*;

@Entity
public class TriggerEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String triggerName;

    private String description;

    @ManyToOne
    @JoinColumn(name = "mood_entry_id")
    private MoodEntry moodEntry;

    public TriggerEntry() {
    }

    public Long getId() {
        return id;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
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
