package com.team2.mentalhealthtracker.repository;

import com.team2.mentalhealthtracker.model.MoodEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MoodEntryRepository extends JpaRepository<MoodEntry, Long> {

    List<MoodEntry> findByUserIdOrderByCreatedAtDesc(Long userId);

    Optional<MoodEntry> findTopByUserIdOrderByCreatedAtDesc(Long userId);
}