package ru.sergalas.perpay.security.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sergalas.perpay.entities.users.entities.User;
import ru.sergalas.perpay.entities.users.repository.UserRepository;
import ru.sergalas.perpay.security.exceptions.UserSignException;
import ru.sergalas.perpay.security.dto.request.SignupRequestDto;

import java.security.Principal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserAuthService {
    public static final Logger LOG = LoggerFactory.getLogger(UserAuthService.class);

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    public User createUser(SignupRequestDto userIn) throws UserSignException {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail(userIn.getEmail());
        user.setPhoneNumber(userIn.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        try {
            LOG.info("User " + user.getUsername() + " created !");
            return userRepository.save(user);
        } catch (Exception e) {
            LOG.error("Error " + e.getMessage());
            throw new UserSignException("Error " + e.getMessage());
        }
    }
}
