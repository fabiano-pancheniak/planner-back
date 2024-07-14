package com.example.planner.exceptions;

public class OwnerEmailIsInvitedException extends RuntimeException{
    public OwnerEmailIsInvitedException(String email){
        super("O email do criador da viagem ("+email+") não pode estar na lista dos convidados.");
    }
}
