USE mental_health_tracker;

INSERT INTO users (username, email, password, created_at)
VALUES
    ('gtyson', 'glenn@example.com', 'hashed_password_123', NOW()),
    ('testuser', 'test@example.com', 'hashed_password_456', NOW());

INSERT INTO mood_entries (
    mood_score,
    anxiety_level,
    stress_level,
    sleep_quality,
    journal_note,
    check_in_date,
    user_id
)
VALUES
    (7, 3, 4, 8, 'Feeling okay today, a little stressed but manageable.', CURDATE(), 1),
    (4, 7, 8, 5, 'Rough day today. Trying to stay calm.', CURDATE(), 2);