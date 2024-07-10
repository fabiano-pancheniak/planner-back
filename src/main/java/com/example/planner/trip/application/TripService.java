package com.example.planner.trip.application;

import com.example.planner.trip.application.dto.ParticipantRequestDto;
import com.example.planner.trip.application.dto.TripRequestDto;
import com.example.planner.trip.domain.Participant;
import com.example.planner.trip.domain.Trip;
import com.example.planner.trip.repository.TripRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {


    private final TripRepository repository;

    private final ParticipantService participantService;

    public TripService(TripRepository repository, ParticipantService participantService) {
        this.repository = repository;
        this.participantService = participantService;
    }

    @Transactional
    public Trip createTrip(TripRequestDto body){
        Trip trip = new Trip();
        trip.setDestino(body.destination());
        trip.setStartsAt(body.startsAt());
        trip.setEndsAt(body.endsAt());
        trip.setIsConfirmed(body.isConfirmed());
        trip.setOwnerEmail(body.ownerEmail());
        trip.setOwnerName(body.ownerName());

        //enviar emails pra quem tiver na lista de email
        //enviar email para o criador da viagem confirmar
        repository.save(trip);
        //preencher tabela de participantes e viagem
        for (String email : body.emailsToInvite()){
            participantService.addParticipantToTrip(new ParticipantRequestDto(email, trip));
        }

        return trip;

    }

    private void sendOwnerEmail(String email){
        //TODO: enviar e redirecionar para a página da viagem
    }

    private void sendParticipantsEmail(List<String> emails){
        //TODO: enviar e redirecionar para a página de exibir o nome (o email já deve estar preenchido)
    }
}
