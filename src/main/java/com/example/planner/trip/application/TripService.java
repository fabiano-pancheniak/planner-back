package com.example.planner.trip.application;

import com.example.planner.participant.application.ParticipantService;
import com.example.planner.participant.application.dto.GuestConfirm;
import com.example.planner.participant.application.dto.ParticipantRequestDto;
import com.example.planner.participant.domain.Participant;
import com.example.planner.trip.application.dto.TripRequestDto;
import com.example.planner.trip.domain.Trip;
import com.example.planner.trip.repository.TripRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {


    private final TripRepository repository;

    private final ParticipantService participantService;

    public TripService(TripRepository repository, ParticipantService participantService) {
        this.repository = repository;
        this.participantService = participantService;
    }

    public List<Trip> getAll(){
        return repository.findAll();
    }
    public Optional<Trip> get(String id){
        return repository.findByPublicId(id);
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

    @Transactional
    public Trip ownerConfirmation(String publicId){
        Trip trip = repository.findByPublicId(publicId).orElseThrow(() -> new RuntimeException("Viagem não encontrada com o id:" +publicId));
        trip.setIsConfirmed(true);
        repository.save(trip);

        List<Participant> participantList = participantService.findByTrip(trip.getId());
        for(Participant participant : participantList){
            String URL = "http://localhost:4200/trip-invite?trip="+trip.getPublicId()+"&email="+participant.getEmail();
            System.out.println(URL);
        }
        return trip;
    }


    private void sendOwnerEmail(String email){
        //TODO: enviar e redirecionar para a página da viagem
    }

    private void sendParticipantsEmail(List<String> emails){
        //TODO: enviar e redirecionar para a página de exibir o nome (o email já deve estar preenchido)
    }

    public Optional<Trip> getByPublicId(String publicId){
        return repository.findByPublicId(publicId);
    }


    public Participant guestsConfirmation(GuestConfirm payload, String publicId){
        Trip trip = this.getByPublicId(publicId).orElseThrow(() -> new RuntimeException("Trip não encontrada com o id: "+publicId));
        Participant participant = participantService.findByEmailAndTripId(payload.email(), trip.getId());

        participant.setName(payload.name());
        participant.setConfirmed(true);

        participantService.updateConfirmation(payload.email());
        return participant;
    }
}
