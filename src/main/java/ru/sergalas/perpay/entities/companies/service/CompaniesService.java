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
import ru.sergalas.perpay.entities.users.entities.Role;
import ru.sergalas.perpay.entities.users.entities.User;
import ru.sergalas.perpay.entities.users.exception.NotAllowException;
import ru.sergalas.perpay.entities.users.exception.UserNotFoundException;
import ru.sergalas.perpay.entities.users.repository.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CompaniesService {

    private final CompaniesRepository companiesRepository;
    private final CompanyMapper companyMapper;
    private final UserRepository userRepository;

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
    public CompanyReadDTO create(CompanyWriteDTO company, Principal principal) throws UserNotFoundException, NotAllowException {
        findUserAndAllowed(principal);
        return Optional.of(company)
                .map(dto -> companyMapper.toEntity(company))
                .map(companiesRepository::save)
                .map(companyMapper::toDTO)
                .orElseThrow();
    }

    @Transactional
    public Optional<CompanyReadDTO> edit(String id, CompanyWriteDTO companyWriteDTO, Principal principal) throws UserNotFoundException, NotAllowException {
        findUserAndAllowed(principal);
        return companiesRepository.findById(UUID.fromString(id))
                .map(dto -> companyMapper.toEntity(companyWriteDTO))
                .map(companiesRepository::saveAndFlush)
                .map(companyMapper::toDTO);
    }

    @Transactional
    public CompanyMessageDTO delete(String id, Principal principal) throws UserNotFoundException, NotAllowException {
        findUserAndAllowed(principal);
        return companiesRepository.findById(UUID.fromString(id))
            .map(company -> {
                companiesRepository.delete(company);
                companiesRepository.flush();
                return new CompanyMessageDTO("Company has deleted");
            }) .orElse (
                    new CompanyMessageDTO("Company has deleted")
            );
    }

    private void findUserAndAllowed(Principal principal) throws UserNotFoundException, NotAllowException {
        User user = userRepository
                .findUserByPhoneNumber(principal.getName())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if(!user.getRoles().contains(Role.ADMIN)) {
                throw new NotAllowException("Not allow");
        }

    }

}
