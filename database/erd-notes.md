# ERD Notes

## Tables
- users
- mood_entries

## Relationships
- One user can have many mood entries
- Each mood entry belongs to one user

## Backend Alignment
This matches the current Spring Boot entities:
- User.java -> users
- MoodEntry.java -> mood_entries