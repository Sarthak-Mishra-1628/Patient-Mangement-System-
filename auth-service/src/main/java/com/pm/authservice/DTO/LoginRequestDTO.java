package com.pm.authservice.DTO;
import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginRequestDTO {

    @NotBlank(message = "Email is required")
    @Email(message = "Enter Valid email")
    private String email;

    @NotBlank(message = "Email is required")
    @Size(min = 8 , message = "Password must be 8 characters long")
    private String password;


}
