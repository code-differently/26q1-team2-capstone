package com.team2.mentalhealthtracker.repository;

import com.team2.mentalhealthtracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
