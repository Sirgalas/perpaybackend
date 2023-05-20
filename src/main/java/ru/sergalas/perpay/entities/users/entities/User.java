package ru.sergalas.perpay.entities.users.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.sergalas.perpay.annotation.UuidGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
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

    @Column(unique = true,nullable = false, name = "phone_number")
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    @Column(length = 3000)
    private String password;

    @OneToOne(mappedBy = "user")
    private UsersCompanies usersCompanies;

    @ElementCollection(targetClass = Role.class)
    @CollectionTable(name="user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Builder.Default
    private Set<Role> roles = new HashSet<>();

    public User (UUID id, String phoneNumber, String email, String password) {
        this.id = id;
        this.phoneNumber = phoneNumber;
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

    @Override
    public String getUsername() {
        return this.getPhoneNumber();
    }
}
