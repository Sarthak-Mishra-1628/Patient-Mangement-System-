package com.pm.patient_service.Exceptions;

public class PatientNotFoundException extends RuntimeException{

    public PatientNotFoundException(String msg){
        super(msg);
    }

}
