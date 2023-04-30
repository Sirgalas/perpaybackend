package ru.sergalas.perpay.entities.users.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.UUID;


@RequiredArgsConstructor
@Data
public class UserReadDTO {

    private UUID id;

    private String phoneNumber;

    private String email;
}
