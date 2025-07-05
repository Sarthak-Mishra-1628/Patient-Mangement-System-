package com.pm.patient_service.Service;
import com.pm.patient_service.grpc.BillingServiceGrpcClient;
import com.pm.patient_service.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import com.pm.patient_service.DTO.*;
import com.pm.patient_service.Entity.Patient;
import com.pm.patient_service.Exceptions.*;
import com.pm.patient_service.Mapper.PatientMapper;
import com.pm.patient_service.Repository.PatientRepo;

@Service
public class PatientService {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private BillingServiceGrpcClient billingServiceGrpcClient;

    @Autowired
    private KafkaProducer kafkaProducer;

    public List<PatientResponseDTO> getPatients(){
        List<Patient> patients = patientRepo.findAll();

        // List<PatientResponseDTO> patientResponseDTO = patients.stream().map(patient -> PatientMapper.toDTO(patient)).toList();
        // List<PatientResponseDTO> patientResponseDTO = patients.stream().map(PatientMapper::toDTO).toList();
        return patients.stream().map(PatientMapper::fromEntitytoDTO).toList();        
    }


    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){

        if(patientRepo.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("Patient already exists with " + patientRequestDTO.getEmail());
        }

        Patient patient = PatientMapper.fromDTOtoEntity(patientRequestDTO);
        Patient newPatient = patientRepo.save(patient);

        billingServiceGrpcClient.createBillingAccount( newPatient.getId().toString() , newPatient.getName() , newPatient.getEmail() );
        kafkaProducer.sendEvent(newPatient);

        return PatientMapper.fromEntitytoDTO(newPatient);
    }


    public PatientResponseDTO updatePatient(UUID id , PatientRequestDTO patientRequestDTO){
        Patient patient = patientRepo.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient with ID + " + id + " not found..."));

        if(patientRepo.existsByEmail(patientRequestDTO.getEmail()) && !patient.getEmail().equals(patientRequestDTO.getEmail()) ){
            throw new EmailAlreadyExistsException("Patient already exists with " + patientRequestDTO.getEmail());
        }

        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        Patient updatedPatient = patientRepo.save(patient);

        return PatientMapper.fromEntitytoDTO(updatedPatient);
    }


    public String deletePatient(UUID id){
        if(patientRepo.existsById(id)){
            patientRepo.deleteById(id);
            return "Patient deleted with id : " + id;
        }

        return "Patient not found with id : " + id;
    }
}
