package ru.sergalas.perpay.entities.companies.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Data
public class CompanyWriteDTO {

    public UUID id;

    public String name;

    public String address;

    public String currentAccounts;
}
