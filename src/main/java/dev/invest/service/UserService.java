package dev.invest.service;

import dev.invest.db.repository.UserRepository;
import dev.invest.mapper.UserMapper;
import dev.invest.model.user.UserDto;
import dev.invest.utils.GenerateUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements Initializer {
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public void initialize() {
        log.info("Инициализируем базу пользователей");
        GenerateUtils.buildCreateUserRequestList()
                .stream()
                .map(userMapper::toEntity)
                .peek(userRepository::save)
                .toList();
        log.info("База пользователей инициализирована успешно!");
    }

    public List<UserDto> findAll() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::toModel)
                .toList();
    }
}
