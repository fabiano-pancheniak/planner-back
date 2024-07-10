package com.example.planner.trip.repository;

import com.example.planner.trip.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, String> {
}
