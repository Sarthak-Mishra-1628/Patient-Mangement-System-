package com.pm.patient_service.Mapper;
import java.time.LocalDate;

import com.pm.patient_service.DTO.*;
import com.pm.patient_service.Entity.Patient;

public class PatientMapper {
    public static PatientResponseDTO fromEntitytoDTO(Patient patient){
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
        patientResponseDTO.setId(patient.getId().toString());
        patientResponseDTO.setEmail(patient.getEmail());
        patientResponseDTO.setAddress(patient.getAddress());
        patientResponseDTO.setName(patient.getName());
        patientResponseDTO.setDateOfBirth(patient.getDateOfBirth().toString());

        return patientResponseDTO;
    }

    public static Patient fromDTOtoEntity(PatientRequestDTO patientRequestDTO){
        Patient patient = new Patient();
        // patient.setId(patientRequestDTO.getId());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setName(patientRequestDTO.getName());
        patient.setDateOfBirth(LocalDate.parse( patientRequestDTO.getDateOfBirth()) );
        patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));

        return patient;
    }
}