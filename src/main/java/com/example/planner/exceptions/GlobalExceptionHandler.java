package com.example.planner.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TripNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleTripNotFoundException(TripNotFoundException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(ex.getMessage()));
    }

    @ExceptionHandler(ParticipantNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleParticipantNotFound(ParticipantNotFoundException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(ex.getMessage()));
    }

    @ExceptionHandler(EmailIsAlreadyInTripException.class)
    public ResponseEntity<ExceptionResponse> handleEmailIsAlreadyInTrip(EmailIsAlreadyInTripException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(ex.getMessage()));
    }

    @ExceptionHandler(OwnerEmailIsInvitedException.class)
    public ResponseEntity<ExceptionResponse> handleOwnerEmailsIsInvited(OwnerEmailIsInvitedException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(ex.getMessage()));
    }

    @ExceptionHandler(StartDateGreaterThanEndDateException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidStartAndEndDate(StartDateGreaterThanEndDateException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(ex.getMessage()));
    }

    @ExceptionHandler(ActivityDateOutOfRangeException.class)
    public ResponseEntity<ExceptionResponse> handleActivityDateOutOfRange(ActivityDateOutOfRangeException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(ex.getMessage()));
    }

}
