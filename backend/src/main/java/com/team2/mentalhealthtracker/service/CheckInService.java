package com.team2.mentalhealthtracker.service;

import com.team2.mentalhealthtracker.dto.MoodEntryRequest;
import com.team2.mentalhealthtracker.dto.MoodEntryResponse;
import com.team2.mentalhealthtracker.model.CheckIn;
import com.team2.mentalhealthtracker.model.SleepQuality;
import com.team2.mentalhealthtracker.model.User;
import com.team2.mentalhealthtracker.repository.CheckInRepository;
import com.team2.mentalhealthtracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckInService {

    private final CheckInRepository checkInRepository;
    private final UserRepository userRepository;

    public CheckInService(
            CheckInRepository checkInRepository,
            UserRepository userRepository
    ) {
        this.checkInRepository = checkInRepository;
        this.userRepository = userRepository;
    }

    public MoodEntryResponse createCheckIn(
            Long userId,
            MoodEntryRequest request
    ) {
        User user = findUser(userId);

        CheckIn checkIn = new CheckIn();
        checkIn.setMoodScore(request.getMoodScore());
        checkIn.setStressLevel(request.getStressLevel());
        checkIn.setSleepQuality(parseSleepQuality(request.getSleepQuality()));
        checkIn.setJournalNotes(request.getJournalNotes());
        checkIn.setTriggers(request.getTriggers());
        checkIn.setCopingStrategiesUsed(request.getCopingStrategiesUsed());
        checkIn.setUser(user);

        CheckIn saved = checkInRepository.save(checkIn);

        return mapToResponse(saved);
    }

    public List<MoodEntryResponse> getCheckInsByUser(
            Long userId
    ) {
        User user = findUser(userId);

        return checkInRepository.findByUserOrderByCreatedAtAsc(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    private SleepQuality parseSleepQuality(String value) {
        if (value == null || value.isBlank()) {
            throw new RuntimeException("Sleep quality is required");
        }

        try {
            return SleepQuality.valueOf(
                    value.trim().toUpperCase()
            );
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException(
                    "Invalid sleep quality. Use GOOD, OKAY, or POOR"
            );
        }
    }

    private MoodEntryResponse mapToResponse(CheckIn checkIn) {
        return new MoodEntryResponse(
                checkIn.getId(),
                checkIn.getMoodScore(),
                checkIn.getStressLevel(),
                checkIn.getSleepQuality().name(),
                checkIn.getJournalNotes(),
                checkIn.getTriggers(),
                checkIn.getCopingStrategiesUsed(),
                checkIn.getCreatedAt()
        );
    }
}