package com.team2.mentalhealthtracker.dto;

public class MoodEntryRequest {

    private Integer moodScore;
    private Integer stressLevel;
    private String sleepQuality;
    private String journalNotes;

    public MoodEntryRequest() {
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
}
