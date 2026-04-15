package com.team2.mentalhealthtracker.controller;

import com.team2.mentalhealthtracker.dto.MoodEntryRequest;
import com.team2.mentalhealthtracker.dto.MoodEntryResponse;
import com.team2.mentalhealthtracker.model.CheckIn;
import com.team2.mentalhealthtracker.model.SleepQuality;
import com.team2.mentalhealthtracker.repository.CheckInRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkins")
@CrossOrigin(origins = "http://localhost:5173")
public class CheckInController {

    private final CheckInRepository checkInRepository;

    public CheckInController(CheckInRepository checkInRepository) {
        this.checkInRepository = checkInRepository;
    }

    @PostMapping
    public ResponseEntity<MoodEntryResponse> createCheckIn(@Valid @RequestBody MoodEntryRequest request) {
        CheckIn checkIn = new CheckIn();

        checkIn.setMoodScore(request.getMoodScore());
        checkIn.setStressLevel(request.getStressLevel());
        checkIn.setSleepQuality(SleepQuality.valueOf(request.getSleepQuality().toUpperCase()));
        checkIn.setJournalNotes(request.getJournalNotes());
        checkIn.setTriggers(request.getTriggers());
        checkIn.setCopingStrategiesUsed(request.getCopingStrategiesUsed());

        CheckIn saved = checkInRepository.save(checkIn);

        MoodEntryResponse response = new MoodEntryResponse(
                saved.getId(),
                saved.getMoodScore(),
                saved.getStressLevel(),
                saved.getSleepQuality().name(),
                saved.getJournalNotes(),
                saved.getTriggers(),
                saved.getCopingStrategiesUsed(),
                saved.getCreatedAt()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<MoodEntryResponse>> getAllCheckIns() {
        List<MoodEntryResponse> responses = checkInRepository.findAllByOrderByCreatedAtAsc()
                .stream()
                .map(checkIn -> new MoodEntryResponse(
                        checkIn.getId(),
                        checkIn.getMoodScore(),
                        checkIn.getStressLevel(),
                        checkIn.getSleepQuality().name(),
                        checkIn.getJournalNotes(),
                        checkIn.getTriggers(),
                        checkIn.getCopingStrategiesUsed(),
                        checkIn.getCreatedAt()
                ))
                .toList();

        return ResponseEntity.ok(responses);
    }
}