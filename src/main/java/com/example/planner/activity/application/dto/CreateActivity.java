package com.example.planner.activity.application.dto;

import java.time.LocalDateTime;

public record CreateActivity(String description, LocalDateTime dateTime, String tripId) {
}
