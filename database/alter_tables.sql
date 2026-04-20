USE mental_health_tracker;

ALTER TABLE mood_entries
    ADD COLUMN energy_level INT NULL,
ADD CONSTRAINT chk_energy_level CHECK (energy_level BETWEEN 1 AND 10);