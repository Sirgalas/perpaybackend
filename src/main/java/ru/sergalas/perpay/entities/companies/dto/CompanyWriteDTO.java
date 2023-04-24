package ru.sergalas.perpay.entities.companies.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import java.util.UUID;

@RequiredArgsConstructor
@Data
public class CompanyWriteDTO {

    private UUID id;

    private String name;

    private String address;

    private String currentAccounts;
}
