package ru.sergalas.perpay.entities.users.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.sergalas.perpay.annotation.UuidGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(unique = true,nullable = false)
    private String username;

    @Column(nullable = false)
    private String firstname;

    private String lastname;

    @Column(unique = true)
    private String email;

    @Column(length = 3000)
    private String password;

    @OneToOne(mappedBy = "user")
    private UsersCompanies usersCompanies;

    public User (UUID id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
