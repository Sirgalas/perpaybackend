package ru.sergalas.perpay.entities.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sergalas.perpay.entities.users.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findUserByPhoneNumber(String phoneNumber);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserById(UUID id);


}
