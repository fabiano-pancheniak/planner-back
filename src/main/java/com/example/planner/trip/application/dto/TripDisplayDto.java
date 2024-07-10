package com.example.planner.trip.application.dto;

import java.time.LocalDateTime;
import java.util.List;

public record TripDisplayDto(
        String destination,
        LocalDateTime startsAt,
        LocalDateTime endsAt,
        List<String> emailsToInvite,
        String ownerName,
        String ownerEmail,
        boolean isConfirmed
) {
}
