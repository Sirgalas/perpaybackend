package ru.sergalas.perpay.entities.payment.repository;

import ru.sergalas.perpay.entities.payment.dto.PaymentReadDTO;
import ru.sergalas.perpay.entities.payment.entity.Payment;

import java.util.List;
import java.util.UUID;

public interface PaymentDsl {
    List<PaymentReadDTO> findByUserIdAndCompanyId(UUID companyId, UUID UserId);
}
