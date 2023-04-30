package ru.sergalas.perpay.entities.payment.dto;

import ru.sergalas.perpay.entities.users.entities.UsersCompanies;

import java.time.LocalDate;
import java.util.UUID;

public class PaymentReadDTO {
    public UUID id;
    public Float payment;
    public LocalDate paymentDate;
}
