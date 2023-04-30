package ru.sergalas.perpay.entities.companies.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sergalas.perpay.entities.companies.dto.CompanyMessageDTO;
import ru.sergalas.perpay.entities.companies.dto.CompanyReadDTO;
import ru.sergalas.perpay.entities.companies.dto.CompanyWriteDTO;
import ru.sergalas.perpay.entities.companies.exceptions.CompanyNotFoundException;
import ru.sergalas.perpay.entities.companies.mappers.CompanyMapper;
import ru.sergalas.perpay.entities.companies.repository.CompaniesRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CompaniesService {

    private final CompaniesRepository companiesRepository;
    private final CompanyMapper companyMapper;

    public List<CompanyReadDTO> findAll() {
        return companiesRepository.findAll()
                .stream()
                .map(companyMapper::toDTO)
                .toList();
    }

    public CompanyReadDTO findOne(String id) throws CompanyNotFoundException {
        return companiesRepository.findById(UUID.fromString(id)).map(companyMapper::toDTO).orElseThrow();
    }

    @Transactional
    public CompanyReadDTO create(CompanyWriteDTO company) {
        return Optional.of(company)
                .map(dto -> companyMapper.toEntity(company))
                .map(companiesRepository::save)
                .map(companyMapper::toDTO)
                .orElseThrow();
    }

    @Transactional
    public Optional<CompanyReadDTO> edit(String id, CompanyWriteDTO companyWriteDTO) {
        return companiesRepository.findById(UUID.fromString(id))
                .map(dto -> companyMapper.toEntity(companyWriteDTO))
                .map(companiesRepository::saveAndFlush)
                .map(companyMapper::toDTO);
    }

    @Transactional
    public boolean delete(String id) {
        return companiesRepository.findById(UUID.fromString(id))
                .map(company -> {
                    companiesRepository.delete(company);
                    companiesRepository.flush();
                    return true;
                }).orElse(false);
    }

}
