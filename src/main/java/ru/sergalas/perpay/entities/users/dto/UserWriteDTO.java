package ru.sergalas.perpay.entities.users.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Data
public class UserWriteDTO {

    public UUID id;

    public String phoneNumber;

    public String email;
}
