package ru.sergalas.perpay.entities.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sergalas.perpay.entities.users.entity.User;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
