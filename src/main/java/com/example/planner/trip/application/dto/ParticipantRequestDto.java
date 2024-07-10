package com.example.planner.trip.application.dto;

import com.example.planner.trip.domain.Trip;

public record ParticipantRequestDto(String email, Trip trip) {
}
