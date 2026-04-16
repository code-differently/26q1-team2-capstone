package com.team2.mentalhealthtracker.controller;

import com.team2.mentalhealthtracker.model.TriggerEntry;
import com.team2.mentalhealthtracker.service.TriggerEntryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/triggers")
@CrossOrigin(origins = "http://localhost:3000")
public class TriggerEntryController {

    private final TriggerEntryService triggerEntryService;

    public TriggerEntryController(TriggerEntryService triggerEntryService) {
        this.triggerEntryService = triggerEntryService;
    }

    @PostMapping
    public TriggerEntry createTriggerEntry(@RequestBody TriggerEntry triggerEntry) {
        return triggerEntryService.saveTriggerEntry(triggerEntry);
    }

    @GetMapping
    public List<TriggerEntry> getAllTriggerEntries() {
        return triggerEntryService.getAllTriggerEntries();
    }
}
