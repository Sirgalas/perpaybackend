package ru.sergalas.perpay.entities.users.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sergalas.perpay.annotation.UuidGenerator;
import ru.sergalas.perpay.entities.companies.entity.Company;
import ru.sergalas.perpay.entities.payment.entity.Payment;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "users_companies")
public class UsersCompanies {

    @Id
    @UuidGenerator
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "subscriber_code")
    private String subscriberCode;

    @OneToMany(mappedBy="usersCompanies")
    private Set<Payment> payments;

    public UsersCompanies(User user, Company company)
    {
        id = UUID.randomUUID();
        this.user = user;
        this.company = company;
    }
}
