package com.example.planner.trip.presentation;

import com.example.planner.trip.application.ParticipantService;
import com.example.planner.trip.application.TripService;
import com.example.planner.trip.application.dto.TripRequestDto;
import com.example.planner.trip.domain.Participant;
import com.example.planner.trip.domain.Trip;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trip")
public class TripResource {

    private final TripService service;

    public TripResource(TripService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Trip> create(@RequestBody TripRequestDto body){
        Trip newTrip = service.createTrip(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTrip);
    }

}
