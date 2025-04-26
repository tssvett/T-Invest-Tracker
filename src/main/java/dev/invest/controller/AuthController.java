package dev.invest.controller;

import dev.invest.model.auth.AuthRequest;
import dev.invest.model.auth.AuthResponse;
import dev.invest.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@Tag(name = AuthController.AUTH_CONTROLLER, description = "API для аутентификации и управления сессиями")
@RequestMapping(AuthController.API_AUTH)
@RequiredArgsConstructor
public class AuthController {
    static final String AUTH_CONTROLLER = "auth-controller";
    static final String API_VERSION = "v1";
    static final String API_PREFIX = "/api/" + API_VERSION;
    static final String API_AUTH = API_PREFIX + "/auth";

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    @Operation(
            summary = "Аутентификация пользователя",
            description = "Возвращает access token и устанавливает refresh token в cookie",
            tags = {AUTH_CONTROLLER}
    )
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody AuthRequest authRequest,
            HttpServletResponse response
    ) {
        log.info("Attempting login for user: {}", authRequest.login());
        AuthResponse authResponse = authenticationService.authenticate(authRequest, response);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/refresh")
    @Operation(
            summary = "Обновление токенов",
            description = "Генерирует новый access token и устанавливает refresh token в cookie",
            tags = {AUTH_CONTROLLER}
    )
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<AuthResponse> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        log.info("Attempting token refresh");
        AuthResponse authResponse = authenticationService.refreshToken(request, response);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/logout")
    @Operation(
            summary = "Завершение сессии",
            description = "Инвалидирует refresh token и очищает cookie",
            tags = {AUTH_CONTROLLER}
    )
    @SecurityRequirement(name = "JWT")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> logout(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        log.info("Processing logout request");
        authenticationService.logout(request, response);
        return ResponseEntity.noContent().build();
    }
}
