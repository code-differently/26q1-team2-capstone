CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       first_name VARCHAR(100) NOT NULL,
                       last_name VARCHAR(100) NOT NULL,
                       email VARCHAR(150) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL
);

CREATE TABLE checkins (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          mood_score INT NOT NULL CHECK (mood_score BETWEEN 1 AND 10),
                          stress_level INT NOT NULL CHECK (stress_level BETWEEN 1 AND 10),
                          sleep_quality VARCHAR(20) NOT NULL,
                          journal_notes TEXT,
                          triggers TEXT,
                          coping_strategies_used TEXT,
                          created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          user_id BIGINT NOT NULL,

                          CONSTRAINT fk_checkins_user
                              FOREIGN KEY (user_id)
                                  REFERENCES users(id)
                                  ON DELETE CASCADE
);