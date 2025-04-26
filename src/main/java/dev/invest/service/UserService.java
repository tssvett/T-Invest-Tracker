package dev.invest.service;

import dev.invest.db.jooq.org.jooq.generated.invest.enums.UserRole;
import dev.invest.db.repository.UserRepository;
import dev.invest.exception.PasswordMismatchException;
import dev.invest.mapper.UserMapper;
import dev.invest.model.user.CreateUserRequest;
import dev.invest.model.user.UpdatePasswordUserRequest;
import dev.invest.model.user.UpdateUserRequest;
import dev.invest.model.user.UserDto;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsManager {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordService passwordService;

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

    public UserDto updatePassword(UpdatePasswordUserRequest request) {
        // Валидация входных данных
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        // Поиск пользователя
        dev.invest.db.jooq.org.jooq.generated.invest.tables.records.UsersRecord user = userRepository.findByLogin(request.username())
                .orElseThrow(() -> new NoSuchElementException("User not found with name: " + request.username()));

        // Проверка старого пароля
        if (!passwordService.checkPassword(request.oldPassword(),user.getPassword())) {
            throw new IllegalArgumentException("Invalid old password");
        }

        // Проверка что новый пароль отличается от старого
        if (passwordService.checkPassword(request.newPassword(), request.oldPassword())) {
            throw new PasswordMismatchException("New password must be different from old one");
        }

        // Хеширование и установка нового пароля
        String newPasswordHash = passwordService.hashPassword(request.newPassword());
        dev.invest.db.jooq.org.jooq.generated.invest.tables.records.UsersRecord usersRecord = userRepository.updatePassword(user.getUserId(), newPasswordHash)
                .orElseThrow(() -> new IllegalArgumentException("Failed to update password for user with name: "
                        + request.username()));

        log.info("Password updated for user with name: {}", request.username());

        return userMapper.toModel(usersRecord);
    }

    @Override
    public void createUser(UserDetails user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateUser(UserDetails user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteUser(String username) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean userExists(String username) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username)
                .map(userMapper::toUser)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь с логином " + username + " не найден"));
    }

    public List<UserRole> getRoles(String login) {
        return List.of(userRepository
                .findByLogin(login)
                .map(dev.invest.db.jooq.org.jooq.generated.invest.tables.records.UsersRecord::getRole)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь с логином " + login + " не найден")));
    }

    public UUID getUserId(String login) {
        return userRepository
                .findByLogin(login)
                .map(dev.invest.db.jooq.org.jooq.generated.invest.tables.records.UsersRecord::getUserId)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь с логином " + login + " не найден"));
    }
}
