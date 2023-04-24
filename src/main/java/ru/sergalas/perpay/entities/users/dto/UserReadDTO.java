package ru.sergalas.perpay.entities.users.dto;

import javax.persistence.Column;
import java.util.UUID;

public class UserReadDTO {

    private UUID id;

    private String phoneNumber;

    private String email;
}
