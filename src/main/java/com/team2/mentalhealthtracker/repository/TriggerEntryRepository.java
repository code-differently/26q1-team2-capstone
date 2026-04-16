package com.team2.mentalhealthtracker.repository;

import com.team2.mentalhealthtracker.model.TriggerEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TriggerEntryRepository extends JpaRepository<TriggerEntry, Long> {
}
