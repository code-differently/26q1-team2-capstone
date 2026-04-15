package com.team2.mentalhealthtracker.repository;

import com.team2.mentalhealthtracker.model.MoodEntry;
import com.team2.mentalhealthtracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MoodEntryRepository extends JpaRepository<MoodEntry, Long> {
    List<MoodEntry> findByUser(User user);
    List<MoodEntry> findByUserId(Long userId);
    List<MoodEntry> findByUserIdOrderByCheckInDateDesc(Long userId);
    List<MoodEntry> findByCheckInDate(LocalDate checkInDate);
}