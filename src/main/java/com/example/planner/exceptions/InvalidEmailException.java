package com.example.planner.exceptions;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String email) {
        super("O email "+email+ " não está em um formato válido.");
    }
}

