package ru.sergalas.perpay.entities.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sergalas.perpay.entities.companies.entity.Company;
import ru.sergalas.perpay.entities.users.entities.User;
import ru.sergalas.perpay.entities.users.entities.UsersCompanies;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersCompaniesRepository extends JpaRepository<UsersCompanies, UUID> {

    Optional<UsersCompanies> findByIdAndAndUser(UUID id, User user);

}
