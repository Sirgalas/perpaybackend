package ru.sergalas.perpay.entities.payment.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.sergalas.perpay.entities.payment.repository.PaymentDsl;
import ru.sergalas.perpay.entities.users.entities.User;
import ru.sergalas.perpay.entities.users.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import ru.sergalas.perpay.entities.payment.dto.PaymentReadDTO;
import ru.sergalas.perpay.entities.payment.dto.PaymentWriteDTO;
import ru.sergalas.perpay.entities.payment.mappers.PaymentMapper;
import ru.sergalas.perpay.entities.payment.repository.PaymentRepository;
import ru.sergalas.perpay.entities.users.repository.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Data
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final UserRepository userRepository;
    private final PaymentDsl paymentDsl;

    public List<PaymentReadDTO> index(String companyId, Principal principal) throws UserNotFoundException {
        User user = userRepository.findUserByPhoneNumber(principal.getName()).orElseThrow(()-> new UserNotFoundException("User not found"));
        return paymentDsl.findByUserIdAndCompanyId(UUID.fromString(companyId),user.getId());
    }

    public PaymentReadDTO create(PaymentWriteDTO paymentWriteDTO) {
        return Optional.of(paymentWriteDTO)
                .map(entity -> paymentMapper.toEntity(paymentWriteDTO))
                .map(paymentRepository::save)
                .map(paymentMapper::toDto)
                .orElseThrow();
    }

    public Optional<PaymentReadDTO> edit (String id, PaymentWriteDTO paymentWriteDTO) {
        return paymentRepository.findById(UUID.fromString(id))
                .map(entity -> paymentMapper.toEntity(paymentWriteDTO))
                .map(paymentRepository::saveAndFlush)
                .map(paymentMapper::toDto);
    }

    public boolean delete(String id) {
        return paymentRepository.findById(UUID.fromString(id))
                .map(payment -> {
                    paymentRepository.delete(payment);
                    paymentRepository.flush();
                    return true;
                }).orElse(false);
    }

}
