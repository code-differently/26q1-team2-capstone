package com.team2.mentalhealthtracker.controller;

import com.team2.mentalhealthtracker.model.MoodEntry;
import com.team2.mentalhealthtracker.service.MoodEntryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mood-entries")
@CrossOrigin(origins = "http://localhost:3000")
public class MoodEntryController {

    private final MoodEntryService moodEntryService;

    public MoodEntryController(MoodEntryService moodEntryService) {
        this.moodEntryService = moodEntryService;
    }

    @PostMapping
    public MoodEntry createMoodEntry(@RequestBody MoodEntry moodEntry) {
        return moodEntryService.saveMoodEntry(moodEntry);
    }

    @GetMapping
    public List<MoodEntry> getAllMoodEntries() {
        return moodEntryService.getAllMoodEntries();
    }
}