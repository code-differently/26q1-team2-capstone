package com.team2.mentalhealthtracker.service;

import com.team2.mentalhealthtracker.model.TriggerEntry;
import com.team2.mentalhealthtracker.repository.TriggerEntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TriggerEntryService {

    private final TriggerEntryRepository triggerEntryRepository;

    public TriggerEntryService(TriggerEntryRepository triggerEntryRepository) {
        this.triggerEntryRepository = triggerEntryRepository;
    }

    public TriggerEntry saveTriggerEntry(TriggerEntry triggerEntry) {
        return triggerEntryRepository.save(triggerEntry);
    }

    public List<TriggerEntry> getAllTriggerEntries() {
        return triggerEntryRepository.findAll();
    }
}
