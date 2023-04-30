package ru.sergalas.perpay.entities.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sergalas.perpay.annotation.UuidGenerator;
import ru.sergalas.perpay.entities.users.entities.UsersCompanies;

import javax.persistence.*;
import java.time.LocalDate;
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

    private Float payment;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @ManyToOne
    @JoinColumn(name="user_company_id", nullable=false)
    private UsersCompanies usersCompanies;

    public Payment(String id,Float payment, LocalDate paymentDate) {
        this.id = UUID.fromString(id);
        this.payment = payment;
        this.paymentDate = paymentDate;
    }

}
