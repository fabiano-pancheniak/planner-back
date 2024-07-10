package com.example.planner.trip.application;

import com.example.planner.trip.application.dto.ParticipantRequestDto;
import com.example.planner.trip.domain.Participant;
import com.example.planner.trip.repository.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantService {
    private final ParticipantRepository repository;

    public ParticipantService(ParticipantRepository repository) {
        this.repository = repository;
    }

    public List<Participant> findAll(){
        return repository.findAll();
    }

    public void addParticipantToTrip(ParticipantRequestDto body){
        Participant participant = new Participant();
        participant.setEmail(body.email());
        participant.setTrip(body.trip());

        repository.save(participant);
    }
}
