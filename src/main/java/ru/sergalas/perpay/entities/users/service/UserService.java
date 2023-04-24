package ru.sergalas.perpay.entities.users.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sergalas.perpay.entities.users.dto.UserReadDTO;
import ru.sergalas.perpay.entities.users.dto.UserWriteDTO;
import ru.sergalas.perpay.entities.users.exception.UserNotFoundException;
import ru.sergalas.perpay.entities.users.mappers.UserMapper;
import ru.sergalas.perpay.entities.users.repository.UserRepository;

import java.security.Principal;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;

    public UserReadDTO edit(UserWriteDTO userWriteDTO, Principal principal) throws UserNotFoundException {
        return repository.findUserByPhoneNumber(principal.getName())
                .map(dto -> userMapper.toEntity(userWriteDTO))
                .map(repository::saveAndFlush)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
    }
}
