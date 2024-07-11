package com.example.planner.trip.repository;

import com.example.planner.trip.domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, Integer> {
    Optional<Trip> findByPublicId(String publicId);


}
