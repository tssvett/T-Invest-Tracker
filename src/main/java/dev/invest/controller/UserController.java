package dev.invest.controller;

import dev.invest.mapper.UserMapper;
import dev.invest.model.user.CreateUserRequest;
import dev.invest.model.user.UpdateUserRequest;
import dev.invest.model.user.UserDto;
import dev.invest.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = UserController.USER_CONTROLLER, description = "API для работы с пользователями")
@RequestMapping(UserController.API_USER)
@RequiredArgsConstructor
public class UserController {
    static final String USER_CONTROLLER = "user-controller";
    static final String API_VERSION = "v1";
    static final String API_PREFIX = "/api/" + API_VERSION;
    static final String API_USER = API_PREFIX + "/user";

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    @Operation(
            summary = "Получить всех пользователей",
            tags = {USER_CONTROLLER}
    )
    public List<UserDto> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{uuid}")
    @Operation(
            summary = "Получить пользователя по его идентификатору",
            tags = {USER_CONTROLLER}
    )
    public UserDto findUserByUid(@PathVariable UUID uuid) {
        return userService.getByUid(uuid);
    }

    @PostMapping
    @Operation(
            summary = "Создать нового пользователя",
            tags = {USER_CONTROLLER}
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserDto createUser(@RequestBody @Valid CreateUserRequest request) {
        return userService.create(request);
    }

    @PutMapping("/{uuid}")
    @Operation(
            summary = "Обновить пользователя по идентификатору",
            tags = {USER_CONTROLLER}
    )
    public UserDto updateUser(@RequestBody @Valid UpdateUserRequest request, @PathVariable UUID uuid) {
        return userService.update(uuid, request);
    }

    @DeleteMapping("/{uuid}")
    @Operation(
            summary = "Удалить пользователя по идентификатору",
            tags = {USER_CONTROLLER}
    )
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID uuid) {
        userService.deleteByUid(uuid);
    }
}
