package com.pm.authservice.DTO;
import lombok.*;

@AllArgsConstructor
@Getter
public class LoginResponse {
    private final String token;
}
