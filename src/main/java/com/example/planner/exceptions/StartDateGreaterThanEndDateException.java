package com.example.planner.exceptions;

public class StartDateGreaterThanEndDateException extends RuntimeException {
    public StartDateGreaterThanEndDateException(String message) {
        super(message);
    }
}
