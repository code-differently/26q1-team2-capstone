INSERT INTO users (
    first_name,
    last_name,
    email,
    password,
    role
) VALUES (
             'Demo',
             'User',
             'demo@example.com',
             '$2a$10$replaceWithRealHashedPassword',
             'USER'
         );

INSERT INTO checkins (
    mood_score,
    stress_level,
    sleep_quality,
    journal_notes,
    triggers,
    coping_strategies_used,
    created_at,
    user_id
) VALUES
      (7, 4, 'GOOD', 'Felt productive today', 'School workload', 'Walked outside', NOW(), 1),
      (5, 6, 'OKAY', 'A little stressed today', 'Deadlines', 'Deep breathing', NOW(), 1),
      (3, 8, 'POOR', 'Very overwhelmed today', 'Lack of sleep', 'Journaling', NOW(), 1);