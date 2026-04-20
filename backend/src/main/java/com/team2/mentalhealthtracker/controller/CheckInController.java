package com.team2.mentalhealthtracker.controller;

import com.team2.mentalhealthtracker.dto.MoodEntryRequest;
import com.team2.mentalhealthtracker.dto.MoodEntryResponse;
import com.team2.mentalhealthtracker.service.CheckInService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkins")
public class CheckInController {
    private final CheckInService checkInService;

    public CheckInController(CheckInService checkInService){
        this.checkInService = checkInService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<MoodEntryResponse> createCheckIn(
            @PathVariable Long userId,
            @RequestBody MoodEntryRequest request){
        return ResponseEntity.ok(
                checkInService.createCheckIn(userId, request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<MoodEntryResponse>> getCheckIns(
            @PathVariable Long userId){
        return ResponseEntity.ok(
                checkInService.getCheckInsByUser(userId));
    }
}