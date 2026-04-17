package com.team2.mentalhealthtracker.repository;

import com.team2.mentalhealthtracker.model.CheckIn;
import com.team2.mentalhealthtracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckInRepository extends JpaRepository<CheckIn, Long> {

    List<CheckIn> findAllByOrderByCreatedAtAsc();

    List<CheckIn> findByUserOrderByCreatedAtAsc(User user);

    List<CheckIn> findTop7ByUserOrderByCreatedAtDesc(User user);
}