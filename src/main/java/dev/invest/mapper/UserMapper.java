package dev.invest.mapper;

import dev.invest.db.jooq.org.jooq.generated.invest.enums.UserRole;
import dev.invest.db.jooq.org.jooq.generated.invest.tables.records.UsersRecord;
import dev.invest.model.user.CreateUserRequest;
import dev.invest.model.user.Role;
import dev.invest.model.user.User;
import dev.invest.model.user.UserDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class UserMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Mapping(target = "id", ignore = true)
    public abstract UserDto toModel(CreateUserRequest userCreateDto);

    @Mapping(target = "id", source = "userId")
    public abstract UserDto toModel(UsersRecord userRecord);


    public abstract UsersRecord toEntity(User user);

    public abstract User toUser(UsersRecord userRecord);


    @Mapping(target = "userId", ignore = true)
    @Mapping(source = "password", target = "password", qualifiedByName = "encryptPassword")
    @Mapping(target = "role", constant = "ROLE_USER")
    public abstract UsersRecord toEntity(CreateUserRequest userCreateDto);

    @Named("encryptPassword")
    String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
