package com.team2.mentalhealthtracker.service;

import com.team2.mentalhealthtracker.model.MoodEntry;
import org.springframework.stereotype.Service;

@Service
public class LocalAiRecommendationService {

    public String generateSuggestion(MoodEntry entry) {
        int mood = entry.getMoodScore() != null ? entry.getMoodScore() : 5;
        int stress = entry.getStressLevel() != null ? entry.getStressLevel() : 5;
        String sleep = safe(entry.getSleepQuality()).toUpperCase();
        String notes = safe(entry.getJournalNotes()).toLowerCase();
        String triggers = safe(entry.getTriggers()).toLowerCase();
        String coping = safe(entry.getCopingStrategiesUsed()).toLowerCase();

        if (mood <= 3 && stress >= 8 && "POOR".equals(sleep)) {
            return "You seem to be carrying a lot right now. Try stepping away for five minutes, take a few slow breaths, and choose one small calming action before tackling anything else.";
        }

        if (mood <= 3 && containsAny(triggers, "alone", "lonely", "isolated")) {
            return "It may help to reconnect with someone safe today, even briefly. A short text, call, or check-in with a trusted person could make the day feel a little lighter.";
        }

        if (stress >= 8 && containsAny(triggers, "school", "exam", "assignment", "homework", "class")) {
            return "Academic stress seems high today. Break your work into one small task, set a short timer, and focus only on the next step instead of the whole workload.";
        }

        if (stress >= 8 && containsAny(triggers, "work", "job", "boss", "shift", "deadline")) {
            return "Work pressure seems to be weighing on you. Try a short reset, stretch your body, and choose one priority to finish before moving to the next thing.";
        }

        if ("POOR".equals(sleep) && mood <= 4) {
            return "Low sleep can make everything feel heavier. Try giving yourself a gentler pace today, stay hydrated, and aim for a calm bedtime routine tonight.";
        }

        if ("POOR".equals(sleep) && stress >= 7) {
            return "Poor sleep and high stress can build on each other. A quieter evening, less screen time before bed, and a short wind-down routine may help tonight.";
        }

        if (containsAny(notes, "anxious", "anxiety", "overthinking", "nervous", "panic")) {
            return "It sounds like your mind may be running fast today. Try grounding yourself with slow breathing, naming what you can see around you, and returning to one task at a time.";
        }

        if (containsAny(notes, "sad", "down", "empty", "drained", "hopeless")) {
            return "Today sounds emotionally heavy. Try choosing one gentle act of care for yourself, like resting, stepping outside, or doing something familiar and comforting.";
        }

        if (containsAny(notes, "angry", "frustrated", "irritated", "mad")) {
            return "There may be a lot of tension under the surface right now. A short walk, stretching, or pausing before responding could help lower the intensity a bit.";
        }

        if (containsAny(coping, "walk", "walking", "exercise", "workout")) {
            return "It’s great that you used movement as a coping tool. Keeping that habit going, even briefly, could help support your mood and stress today.";
        }

        if (containsAny(coping, "music", "journal", "journaling", "breathing", "meditation", "prayer")) {
            return "You already used a healthy coping strategy today. Consider returning to that same support again later if stress builds back up.";
        }

        if (mood >= 7 && stress <= 4 && "GOOD".equals(sleep)) {
            return "You seem to be in a steadier place today. This could be a good time to reinforce what is helping and carry one positive habit into tomorrow.";
        }

        if (mood >= 6 && stress >= 6) {
            return "You seem to be holding it together while still carrying stress. A short pause, some water, and a reset before your next task could help keep things balanced.";
        }

        return "Take a moment to check in with yourself today. A few slow breaths, a short break, and one small supportive action can make the day feel more manageable.";
    }

    private boolean containsAny(String text, String... keywords) {
        for (String keyword : keywords) {
            if (text.contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    private String safe(String value) {
        return value == null ? "" : value.trim();
    }
}