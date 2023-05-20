package ru.sergalas.perpay.entities.companies.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.sergalas.perpay.entities.users.entities.User;
import ru.sergalas.perpay.entities.companies.dto.CompanyReadDTO;
import ru.sergalas.perpay.entities.companies.entity.Company;
import ru.sergalas.perpay.entities.companies.mappers.CompanyMapper;
import ru.sergalas.perpay.entities.users.exception.UserNotFoundException;
import ru.sergalas.perpay.entities.users.repository.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class CompanyDslImpl implements CompanyDsl {

    private final JdbcTemplate jdbcTemplate;
    private final CompanyMapper companyMapper;
    private final UserRepository userRepository;

    private final static String FIND_ALL_USERS_COMPANIES = """
            SELECT 
                id,
                name,
                address,
                current_accounts
            FROM 
                companies
            WHERE     
                id in (
                    SELECT
                        id
                    FROM
                        users_companies
                    WHERE 
                        user_id = ? 
                )
                ORDER BY name
            """;

    @Override
    public List<CompanyReadDTO> getUserCompaniesByPrincipal(Principal principal) throws UserNotFoundException {
        User user = userRepository
                .findUserByPhoneNumber(principal.getName())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return jdbcTemplate.query(
                FIND_ALL_USERS_COMPANIES,
                (rs, rowNum) -> companyMapper.toDTO(
                        new Company(
                             UUID.fromString(rs.getString("id")),
                             rs.getString("name"),
                             rs.getString("address"),
                             rs.getString("current_accounts")
                        )
                ),
                user.getId()

        );
    }
}
