package com.example.planner.exceptions;

public class EmailIsAlreadyInTripException extends RuntimeException {
    public EmailIsAlreadyInTripException(String email, String tripId) {
        super("O email " + email + " já está registrado na viagem " + tripId);
    }
}