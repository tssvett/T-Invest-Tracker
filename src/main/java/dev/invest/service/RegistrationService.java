package dev.invest.service;

import dev.invest.db.jooq.org.jooq.generated.invest.tables.records.UsersRecord;
import dev.invest.db.repository.UserRepository;
import dev.invest.exception.AlreadyExistsException;
import dev.invest.mapper.UserMapper;
import dev.invest.model.user.CreateUserRequest;
import dev.invest.model.user.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordService passwordService;

    public UserDto register(CreateUserRequest userCreateDTO) {

        checkEmailUnique(userCreateDTO.email());

        UsersRecord user = userMapper.toEntity(userCreateDTO);

        user.setPassword(passwordService.hashPassword(userCreateDTO.password()));

        UsersRecord savedUser = userRepository.save(user);

        log.info("User with email '{}' successfully created", user.getEmail());

        return userMapper.toModel(savedUser);
    }

    private void checkEmailUnique(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new AlreadyExistsException("User with email '" + email + "' already exists");
        }
    }
}