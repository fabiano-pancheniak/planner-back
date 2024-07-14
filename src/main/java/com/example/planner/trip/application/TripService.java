package com.example.planner.trip.application;

import com.example.planner.mail.EmailService;
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
import java.util.UUID;

@Service
public class TripService {


    private final TripRepository repository;
    private final EmailService emailService;
    private final ParticipantService participantService;

    public TripService(TripRepository repository, EmailService emailService, ParticipantService participantService) {
        this.repository = repository;
        this.emailService = emailService;
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
        trip.setPublicId(UUID.randomUUID().toString());
        trip.setDestino(body.destination());
        trip.setStartsAt(body.startsAt());
        trip.setEndsAt(body.endsAt());
        trip.setIsConfirmed(body.isConfirmed());
        trip.setOwnerEmail(body.ownerEmail());
        trip.setOwnerName(body.ownerName());

        repository.save(trip);

        this.sendOwnerEmail(body.ownerEmail(), trip.getPublicId());

        for (String email : body.emailsToInvite()){
            this.addParticipantToTrip(new ParticipantRequestDto(email, trip.getId()));
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
            sendParticipantEmail(participant.getEmail(), trip.getOwnerName(), URL);
        }
        return trip;
    }

    private void sendOwnerEmail(String email, String id){
        String URL = "http://localhost:8080/api/trip/"+id+"/confirm";
        String subject = "Confirmação de viagem!";
        String body = "Falta pouco para sua próxima viagem!\nConfirme através da seguinte URL: " + URL;

        emailService.sendEmail(email, subject, body);
    }

    private void sendParticipantEmail(String email, String name, String URL){
        String subject = "Viagem confirmada!";
        String body = "Você recebeu um convite para participar da viagem de "+name+"!\nConfirme sua presença através do link: "+URL;
        this.emailService.sendEmail(email, subject, body);
        //TODO: VERIFICAR DEMORA ~11s TRÊS EMAILS
    }

    public Optional<Trip> getByPublicId(String publicId){
        return repository.findByPublicId(publicId);
    }
    public Optional<Trip> getById(Integer id){return repository.findById(id);};


    public Participant guestsConfirmation(GuestConfirm payload, String publicId){
        Trip trip = this.getByPublicId(publicId).orElseThrow(() -> new RuntimeException("Trip não encontrada com o id: "+publicId));
        Participant participant = participantService.findByTripIdAndEmail(trip.getId(), payload.email());

        participant.setName(payload.name());
        participant.setConfirmed(true);

        participantService.updateConfirmation(payload.email());
        return participant;
    }

    public void inviteParticipant(ParticipantRequestDto payload){
        Trip trip = repository.findById(payload.tripId()).orElseThrow(() -> new RuntimeException("Viagem com o id "+payload.tripId()+" não encontrada."));
        this.addParticipantToTrip(payload);
        String URL = "http://localhost:4200/trip-invite?trip="+trip.getPublicId()+"&email="+payload.email();

        this.sendParticipantEmail(payload.email(), trip.getOwnerName(), URL);
    }

    private void addParticipantToTrip(ParticipantRequestDto payload){
        Trip trip = repository.findById(payload.tripId()).orElseThrow(() -> new RuntimeException("Viagem com o id "+payload.tripId()+" não encontrada."));
        Participant participant = new Participant();
        participant.setEmail(payload.email());
        participant.setTrip(trip);
        participant.setConfirmed(false);

        participantService.save(participant);
    }

}
