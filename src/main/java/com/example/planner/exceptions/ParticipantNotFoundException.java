package com.example.planner.exceptions;

public class ParticipantNotFoundException extends RuntimeException{
    public ParticipantNotFoundException(String email, String tripId){
        super("Usuário não encontrado com o email: "+email+" na viagem: "+tripId);
    }
    public ParticipantNotFoundException(String email){
        super("Usuário não encontrado com o email: "+email);
    }
}