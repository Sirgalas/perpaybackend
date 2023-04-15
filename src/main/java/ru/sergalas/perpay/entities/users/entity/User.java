package ru.sergalas.perpay.entities.users.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import ru.sergalas.perpay.annotation.UuidGenerator;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "users")
public class User {

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
}
