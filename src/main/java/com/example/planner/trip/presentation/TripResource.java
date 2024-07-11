package com.example.planner.trip.presentation;

import com.example.planner.participant.application.dto.GuestConfirm;
import com.example.planner.participant.domain.Participant;
import com.example.planner.trip.application.TripService;
import com.example.planner.trip.application.dto.TripRequestDto;
import com.example.planner.trip.domain.Trip;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/trip")
public class TripResource {

    private final TripService service;

    public TripResource(TripService service) {
        this.service = service;
    }


    @GetMapping()
    public ResponseEntity<List<Trip>> getAll(){
        List<Trip> tripList = service.getAll();

        return ResponseEntity.ok().body(tripList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Trip> get(@PathVariable String id){
        Optional<Trip> tripOpt = service.get(id);

        if(tripOpt.isPresent()){
            Trip trip = tripOpt.get();
            return ResponseEntity.status(HttpStatus.OK).body(trip);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/{id}/confirm")
    public ResponseEntity<Trip> confirm(@PathVariable String id){
        Trip trip = service.ownerConfirmation(id);
        return ResponseEntity.status(HttpStatus.OK).body(trip);
    }

    @PostMapping
    public ResponseEntity<Trip> create(@RequestBody TripRequestDto body){
        Trip newTrip = service.createTrip(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTrip);
    }

    @PostMapping("/{id}/guest-confirm")
    public ResponseEntity<Participant> confirmGuest(@PathVariable String id, @RequestBody GuestConfirm payload){
        Participant participant = service.guestsConfirmation(payload, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(participant);
    }

}
