CREATE DATABASE IF NOT EXISTS mental_health_tracker;
USE mental_health_tracker;

DROP TABLE IF EXISTS mood_entries;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE mood_entries (
                              id BIGINT PRIMARY KEY AUTO_INCREMENT,
                              mood_score INT NOT NULL,
                              anxiety_level INT NOT NULL,
                              stress_level INT NOT NULL,
                              sleep_quality INT NOT NULL,
                              journal_note VARCHAR(1000),
                              check_in_date DATE NOT NULL,
                              user_id BIGINT NOT NULL,

                              CONSTRAINT fk_mood_entries_user
                                  FOREIGN KEY (user_id) REFERENCES users(id)
                                      ON DELETE CASCADE,

                              CONSTRAINT chk_mood_score
                                  CHECK (mood_score BETWEEN 1 AND 10),

                              CONSTRAINT chk_anxiety_level
                                  CHECK (anxiety_level BETWEEN 1 AND 10),

                              CONSTRAINT chk_stress_level
                                  CHECK (stress_level BETWEEN 1 AND 10),

                              CONSTRAINT chk_sleep_quality
                                  CHECK (sleep_quality BETWEEN 1 AND 10)
);