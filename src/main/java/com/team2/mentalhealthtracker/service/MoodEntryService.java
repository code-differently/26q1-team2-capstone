package com.team2.mentalhealthtracker.service;

import com.team2.mentalhealthtracker.repository.CheckInRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoodEntryService {

    private final CheckInRepository moodEntryRepository;

    public MoodEntryService(CheckInRepository moodEntryRepository) {
        this.moodEntryRepository = moodEntryRepository;
    }

    public MoodEntry saveMoodEntry(MoodEntry moodEntry) {
        return moodEntryRepository.save(moodEntry);
    }

    public List<MoodEntry> getAllMoodEntries() {
        return moodEntryRepository.findAll();
    }
}