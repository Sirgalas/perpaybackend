package ru.sergalas.perpay.security.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupRequestDto {

    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "username is required")
    private String phoneNumber;
    @NotBlank(message = "password is required")
    @Size(min = 3, message = "minimum number of characters 3")
    private String password;

}
