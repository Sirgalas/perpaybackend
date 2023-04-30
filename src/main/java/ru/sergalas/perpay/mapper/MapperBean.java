package ru.sergalas.perpay.mapper;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sergalas.perpay.entities.companies.mappers.CompanyMapper;
import ru.sergalas.perpay.entities.payment.mappers.PaymentMapper;
import ru.sergalas.perpay.entities.users.mappers.UserMapper;

@Configuration
public class MapperBean {

    @Bean
    public UserMapper userMapper() {
        return Mappers.getMapper(UserMapper.class);
    }

    @Bean
    public CompanyMapper companyMapper() {
        return Mappers.getMapper(CompanyMapper.class);
    }

    @Bean
    public PaymentMapper paymentMapper() {return Mappers.getMapper(PaymentMapper.class);}
}
