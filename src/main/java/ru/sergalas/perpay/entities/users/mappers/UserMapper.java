package ru.sergalas.perpay.entities.users.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.sergalas.perpay.entities.users.entities.User;
import ru.sergalas.perpay.entities.users.dto.UserReadDTO;
import ru.sergalas.perpay.entities.users.dto.UserWriteDTO;

import java.util.UUID;

@Mapper(imports = UUID.class)
public interface UserMapper {

    UserReadDTO toDto(User user);

    @Mapping(
            target="id",
            source="id",
            defaultExpression = "java( UUID.randomUUID())"
    )
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "usersCompanies", ignore = true)
    User toEntity(UserWriteDTO userWriteDTO);
}
