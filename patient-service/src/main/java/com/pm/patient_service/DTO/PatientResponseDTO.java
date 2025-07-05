package com.pm.patient_service.DTO;
import lombok.*;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class PatientResponseDTO {
    private String id , name , email , address , dateOfBirth;
}