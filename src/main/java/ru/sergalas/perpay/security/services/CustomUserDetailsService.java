package ru.sergalas.perpay.security.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sergalas.perpay.entities.users.entities.User;
import ru.sergalas.perpay.entities.users.repository.UserRepository;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = userRepository.findUserByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Пользователь с таким email не найден")
                );
        return build(user);
    }

    public User loadUserBuId(UUID id) {
        return userRepository.findUserById(id).orElse(null);
    }

    public static User build(User user) {
        return new User(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
        );// todo передалать под dto
    }

}
