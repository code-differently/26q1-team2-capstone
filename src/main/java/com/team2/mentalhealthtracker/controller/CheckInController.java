package com.team2.mentalhealthtracker.controller;

import com.team2.mentalhealthtracker.model.MoodEntry;
import com.team2.mentalhealthtracker.service.MoodEntryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkins")
@CrossOrigin(origins = "http://localhost:3000")
public class CheckInController {

    private final MoodEntryService moodEntryService;

    public CheckInController(MoodEntryService moodEntryService) {
        this.moodEntryService = moodEntryService;
    }

    @PostMapping
    public MoodEntry createCheckIn(@RequestBody MoodEntry moodEntry) {
        return moodEntryService.saveMoodEntry(moodEntry);
    }

    @GetMapping
    public List<MoodEntry> getAllCheckIns() {
        return moodEntryService.getAllMoodEntries();
    }
}