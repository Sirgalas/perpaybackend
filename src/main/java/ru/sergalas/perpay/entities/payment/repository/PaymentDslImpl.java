package ru.sergalas.perpay.entities.payment.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.sergalas.perpay.entities.payment.dto.PaymentReadDTO;
import ru.sergalas.perpay.entities.payment.entity.Payment;
import ru.sergalas.perpay.entities.payment.mappers.PaymentMapper;


import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class PaymentDslImpl implements PaymentDsl{

    private final JdbcTemplate jdbcTemplate;
    private final PaymentMapper paymentMapper;

    private final static String FIND_BY_COMPANY_AND_USER_ID = """
            SELECT 
                id,
                payment,
                payment_date 
            FROM payment
            WHERE user_company_id in (
                SELECT id 
                FROM users_companies
                WHERE company_id = ?
                AND user_id = ?
            )
            ORDER BY payment_date
    """;

    @Override
    public List<PaymentReadDTO> findByUserIdAndCompanyId(UUID companyId, UUID userId) {
        return jdbcTemplate.query(
                FIND_BY_COMPANY_AND_USER_ID,
                (rs, rowNum) -> paymentMapper.toDto(
                        new Payment(
                                rs.getString("id"),
                                rs.getFloat("payment"),
                                rs.getDate("payment_date").toLocalDate()
                        )
                ),
            companyId,
            userId
        );
    }
}
