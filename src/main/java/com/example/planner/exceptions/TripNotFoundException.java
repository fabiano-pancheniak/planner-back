package com.example.planner.exceptions;

public class TripNotFoundException extends RuntimeException{
    public TripNotFoundException(String id){
        super("Viagem não encontrada com o id: "+id);
    }
}
