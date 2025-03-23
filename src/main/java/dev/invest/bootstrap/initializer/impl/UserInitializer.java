package dev.invest.bootstrap.initializer.impl;

import dev.invest.bootstrap.initializer.Initializer;
import dev.invest.db.repository.UserRepository;
import dev.invest.mapper.UserMapper;
import dev.invest.utils.GenerateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserInitializer implements Initializer {
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
}
