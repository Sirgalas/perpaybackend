package ru.sergalas.perpay.security.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTTokenSuccessResponseDto {
    private boolean success;
    private String jwt;
}
