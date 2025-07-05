package com.pm.patient_service.DTO;
import com.pm.patient_service.DTO.Validators.CreatePatientValidationGroup;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatientRequestDTO {

    @NotBlank(message = "Name is required")
    @Size(max=100 , message = "Name can't exceed 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Date of Birth is required")
    private String dateOfBirth;

    @NotBlank(groups=CreatePatientValidationGroup.class , message = "Registered Date is required")
    private String registeredDate;
}