package com.example.planner.participant.repository;

import com.example.planner.participant.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, String> {
    Optional<Participant> findByEmailAndTripId(String email, Integer tripId);

    List<Participant> findByTripId(Integer tripId);

    Optional<Participant> findByEmail(String email);
}
