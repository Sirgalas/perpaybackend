package ru.sergalas.perpay.entities.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sergalas.perpay.entities.users.entities.UsersCompanies;

import java.util.UUID;

@Repository
public interface UsersCompaniesRepository extends JpaRepository<UsersCompanies, UUID> {
}
