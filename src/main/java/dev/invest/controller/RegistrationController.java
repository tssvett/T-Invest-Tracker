package dev.invest.controller;

import dev.invest.model.user.CreateUserRequest;
import dev.invest.model.user.UserDto;
import dev.invest.service.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@Tag(name = UserController.USER_CONTROLLER, description = "API для работы с пользователями")
@RequestMapping(RegistrationController.API_REGISTER)
@RequiredArgsConstructor
public class RegistrationController {
    static final String REGISTRATION_CONTROLLER = "registration-controller";
    static final String API_VERSION = "v1";
    static final String API_PREFIX = "/api/" + API_VERSION;
    static final String API_REGISTER = API_PREFIX + "/register";

    private final RegistrationService registrationService;

    @PostMapping
    @Operation(
            summary = "Регистрация пользователя",
            tags = {REGISTRATION_CONTROLLER}
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserDto register(@RequestBody @Valid CreateUserRequest request) {
        return registrationService.register(request);
    }
}