package ru.sergalas.perpay.entities.users.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sergalas.perpay.entities.companies.dto.CompanyReadDTO;
import ru.sergalas.perpay.entities.companies.entity.Company;
import ru.sergalas.perpay.entities.companies.exceptions.CompanyNotFoundException;
import ru.sergalas.perpay.entities.companies.mappers.CompanyMapper;
import ru.sergalas.perpay.entities.companies.repository.CompaniesRepository;
import ru.sergalas.perpay.entities.users.dto.UserReadDTO;
import ru.sergalas.perpay.entities.users.dto.UserWriteDTO;
import ru.sergalas.perpay.entities.users.entities.User;
import ru.sergalas.perpay.entities.users.entities.UsersCompanies;
import ru.sergalas.perpay.entities.users.exception.UserNotFoundException;
import ru.sergalas.perpay.entities.users.mappers.UserMapper;
import ru.sergalas.perpay.entities.users.repository.UserRepository;
import ru.sergalas.perpay.entities.users.repository.UsersCompaniesRepository;

import java.security.Principal;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;
    private final CompaniesRepository companiesRepository;
    private final CompanyMapper companyMapper;
    private final UsersCompaniesRepository usersCompaniesRepository;


    public UserReadDTO edit(UserWriteDTO userWriteDTO, Principal principal) throws UserNotFoundException {
        return repository.findUserByPhoneNumber(principal.getName())
                .map(dto -> userMapper.toEntity(userWriteDTO))
                .map(repository::saveAndFlush)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
    }

    public CompanyReadDTO addCompany(String companyId, Principal principal) throws CompanyNotFoundException {
        Company companies = companiesRepository.findById(UUID.fromString(companyId)).orElseThrow(() -> new CompanyNotFoundException("Company not found"));
        repository.findUserByPhoneNumber(principal.getName())
                .map(user -> new UsersCompanies(user,companies));
        return companyMapper.toDTO(companies);
    }

    public CompanyReadDTO editSubscriberCode(String id, String subscriberCode, Principal principal) throws CompanyNotFoundException {
        User user = repository.findUserByPhoneNumber(principal.getName())
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
        return usersCompaniesRepository.findByIdAndAndUser(UUID.fromString(id),user)
                .map(usersCompanies -> {
                    usersCompanies.setSubscriberCode(subscriberCode);
                    return usersCompanies;
                })
                .map(usersCompaniesRepository::saveAndFlush)
                .map(usersCompanies -> {
                    try {
                        return companiesRepository.findById(usersCompanies.getCompany().getId()).orElseThrow(() -> new CompanyNotFoundException("Company not found"));
                    } catch (CompanyNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(companyMapper::toDTO)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found"));
    }
}
