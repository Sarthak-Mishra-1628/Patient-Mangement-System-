package com.pm.patient_service.Exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
    
    public EmailAlreadyExistsException(String msg){
        super(msg);
    }
}
