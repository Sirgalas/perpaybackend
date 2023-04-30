package ru.sergalas.perpay.entities.companies.repository;

import ru.sergalas.perpay.entities.companies.dto.CompanyReadDTO;
import ru.sergalas.perpay.entities.companies.entity.Company;
import ru.sergalas.perpay.entities.users.exception.UserNotFoundException;

import java.security.Principal;
import java.util.List;

public interface CompanyDsl {

    List<CompanyReadDTO> getUserCompaniesByPrincipal (Principal principal) throws UserNotFoundException;
}
