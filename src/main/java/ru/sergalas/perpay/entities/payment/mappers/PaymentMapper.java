package ru.sergalas.perpay.entities.payment.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.sergalas.perpay.entities.payment.dto.PaymentReadDTO;
import ru.sergalas.perpay.entities.payment.dto.PaymentWriteDTO;
import ru.sergalas.perpay.entities.payment.entity.Payment;

import java.util.UUID;

@Mapper(imports = UUID.class)
public interface PaymentMapper {
    PaymentReadDTO toDto(Payment payment);

    @Mapping(target = "id", source = "id", defaultExpression = "java( UUID.randomUUID())")
    Payment toEntity(PaymentWriteDTO paymentWriteDTO);
}
