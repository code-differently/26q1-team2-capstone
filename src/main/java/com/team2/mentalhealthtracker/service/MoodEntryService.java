package com.team2.mentalhealthtracker.service;

import com.team2.mentalhealthtracker.model.MoodEntry;
import com.team2.mentalhealthtracker.repository.MoodEntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoodEntryService {

    private final MoodEntryRepository moodEntryRepository;

    public MoodEntryService(MoodEntryRepository moodEntryRepository) {
        this.moodEntryRepository = moodEntryRepository;
    }

    public MoodEntry saveMoodEntry(MoodEntry moodEntry) {
        return moodEntryRepository.save(moodEntry);
    }

    public List<MoodEntry> getAllMoodEntries() {
        return moodEntryRepository.findAll();
    }
}