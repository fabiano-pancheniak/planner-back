package com.example.planner.participant.application;

import com.example.planner.exceptions.ParticipantNotFoundException;
import com.example.planner.participant.application.dto.ParticipantRequestDto;
import com.example.planner.participant.domain.Participant;
import com.example.planner.participant.repository.ParticipantRepository;
import com.example.planner.trip.application.TripService;
import com.example.planner.participant.application.dto.GuestConfirm;
import com.example.planner.trip.domain.Trip;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {
    private final ParticipantRepository repository;

    public ParticipantService(ParticipantRepository repository) {
        this.repository = repository;
    }

    public List<Participant> findAll(){
        return repository.findAll();
    }

    public List<Participant> findByTrip(Integer id){
        return repository.findByTripId(id);
    }

    public Participant findByTripIdAndEmail(Integer tripId, String email){
        return repository.findByEmailAndTripId(email, tripId).orElseThrow(() -> new ParticipantNotFoundException(email, tripId.toString()));
    }

    public void updateConfirmation(String email){
        Participant participant = repository.findByEmail(email).orElseThrow(() -> new ParticipantNotFoundException(email));

        participant.setConfirmed(true);
        repository.save(participant);
    }

    public void save(Participant participant){
        repository.save(participant);
    }

}
