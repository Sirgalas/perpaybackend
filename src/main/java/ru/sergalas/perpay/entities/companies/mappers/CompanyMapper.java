package ru.sergalas.perpay.entities.companies.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.sergalas.perpay.entities.companies.dto.CompanyReadDTO;
import ru.sergalas.perpay.entities.companies.dto.CompanyWriteDTO;
import ru.sergalas.perpay.entities.companies.entity.Company;

import java.util.UUID;

@Mapper(imports = UUID.class)
public interface CompanyMapper {

    CompanyReadDTO toDTO(Company companies);

    @Mapping(target="id", source="id", defaultExpression = "java( UUID.randomUUID())")
    Company toEntity(CompanyWriteDTO dto);
}
