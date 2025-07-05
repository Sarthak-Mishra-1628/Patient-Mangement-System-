package com.pm.patient_service.Controller;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.pm.patient_service.DTO.*;
import com.pm.patient_service.DTO.Validators.CreatePatientValidationGroup;
import com.pm.patient_service.Service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.*;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient" , description = "API for Patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/getAll")
    @Operation(summary = "Get Patients")
    public ResponseEntity<List<PatientResponseDTO>> getAll(){
        return ResponseEntity.ok().body(patientService.getPatients());
    }


    @PostMapping("/new")
    @Operation(summary = "Create new Patient")
    public ResponseEntity<PatientResponseDTO> createPatient(@Validated({Default.class , CreatePatientValidationGroup.class}) 
                                                            @RequestBody PatientRequestDTO patientRequestDTO){
        return ResponseEntity.ok().body(patientService.createPatient(patientRequestDTO));
    }


    @PutMapping("/update/{id}")
    @Operation(summary = "Update Patient")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id , @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO){
        return ResponseEntity.ok().body(patientService.updatePatient(id,patientRequestDTO));
    }

    @DeleteMapping("/delet/{id}")
    @Operation(summary = "Delete Patient")
    public ResponseEntity<String> deletePatient(@PathVariable UUID id){
        return ResponseEntity.ok().body(patientService.deletePatient(id));
    }
}