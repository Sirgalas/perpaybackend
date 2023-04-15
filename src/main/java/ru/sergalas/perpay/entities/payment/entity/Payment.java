package ru.sergalas.perpay.entities.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sergalas.perpay.annotation.UuidGenerator;
import ru.sergalas.perpay.entities.users.entities.UsersCompanies;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne
    @JoinColumn(name="user_company_id", nullable=false)
    private UsersCompanies usersCompanies;

}
