package dev.invest.service;

import dev.invest.db.repository.UserRepository;
import dev.invest.mapper.UserMapper;
import dev.invest.model.user.CreateUserRequest;
import dev.invest.model.user.UpdateUserRequest;
import dev.invest.model.user.UserDto;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> findAll() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::toModel)
                .toList();
    }

    public UserDto create(CreateUserRequest userCreateDto) {
        return userMapper.toModel(userRepository.save(userMapper.toEntity(userCreateDto)));
    }

    public UserDto getByUid(UUID uid) {
        return userMapper.toModel(userRepository
                .getById(uid)
                .orElseThrow(() -> new NoSuchElementException("Пользователь с uid " + uid + " не найден")));
    }

    public void deleteByUid(UUID uid) {
        userRepository.delete(uid)
                .orElseThrow(() -> new NoSuchElementException("Пользователь с uid " + uid + " не найден"));
        log.info("Пользователь {} удален", uid);
    }

    public UserDto update(UUID uid, UpdateUserRequest userRequest) {
        return userRepository.update(uid, userRequest)
                .map(userMapper::toModel)
                .orElseThrow(() -> new NoSuchElementException("Пользователь с uid " + uid + " не найден"));
    }
}
