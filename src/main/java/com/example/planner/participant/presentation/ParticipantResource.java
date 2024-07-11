package com.example.planner.participant.presentation;

import com.example.planner.participant.application.ParticipantService;
import com.example.planner.participant.application.dto.GuestConfirm;
import com.example.planner.participant.domain.Participant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participant")
public class ParticipantResource {
    private final ParticipantService service;

    public ParticipantResource(ParticipantService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Participant>> getAll(){
        List<Participant> participantList =  service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(participantList);
    }


}
