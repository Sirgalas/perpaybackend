package ru.sergalas.perpay.entities.companies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sergalas.perpay.entities.companies.entity.Company;

import java.util.UUID;

@Repository
public interface CompaniesRepository extends JpaRepository<Company, UUID> {
}
