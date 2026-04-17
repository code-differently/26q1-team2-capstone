package com.team2.mentalhealthtracker.service;

import com.team2.mentalhealthtracker.model.MoodEntry;
import com.team2.mentalhealthtracker.repository.MoodEntryRepository;
import org.springframework.stereotype.Service;

@Service
public class AiRecommendationService {

    private final MoodEntryRepository moodEntryRepository;
    private final LocalAiRecommendationService localAiRecommendationService;

    public AiRecommendationService(
            MoodEntryRepository moodEntryRepository,
            LocalAiRecommendationService localAiRecommendationService
    ) {
        this.moodEntryRepository = moodEntryRepository;
        this.localAiRecommendationService = localAiRecommendationService;
    }

    public String generateSuggestion(Long userId) {
        MoodEntry latestEntry = moodEntryRepository
                .findTopByUserIdOrderByCreatedAtDesc(userId)
                .orElse(null);

        if (latestEntry == null) {
            return "Please submit a check-in to receive a personalized wellness suggestion.";
        }

        return localAiRecommendationService.generateSuggestion(latestEntry);
    }
}