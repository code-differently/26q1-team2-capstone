package com.team2.mentalhealthtracker.repository;

import com.team2.mentalhealthtracker.model.MoodEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoodEntryRepository extends JpaRepository<MoodEntry, Long> {
}