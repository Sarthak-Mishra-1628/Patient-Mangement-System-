package com.pm.patient_service.Exceptions;
import java.util.*;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException exception){
        Map<String,String> errors = new HashMap<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        for(FieldError x : fieldErrors){
            errors.put(x.getField() , x.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(errors);        
    }


    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception){
        Map<String,String> errors = new HashMap<>();
        errors.put("Message","Patient already exists with given email address");

        return ResponseEntity.badRequest().body(errors);
    }


    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Map<String,String>> handlePatientNotFoundException(PatientNotFoundException exception){
        Map<String,String> errors = new HashMap<>();
        errors.put("Message","Patient not found");

        return ResponseEntity.badRequest().body(errors);
    }

}
