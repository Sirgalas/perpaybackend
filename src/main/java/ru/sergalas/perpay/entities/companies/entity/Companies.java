package ru.sergalas.perpay.entities.companies.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sergalas.perpay.annotation.UuidGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "companies")
public class Companies {

    @Id
    @UuidGenerator
    private UUID id;

    private String name;

    private String address;

    @Column(name = "current_accounts")
    private String currentAccounts;

}
