package dev.invest.controller;

import dev.invest.bootstrap.DatabaseBootstrap;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(
        name = InitializeController.INITIALIZER_CONTROLLER,
        description = "API Инициализатора бд приложения"
)
@SecurityRequirement(name = "JWT")
@RequestMapping(InitializeController.API_INITIALIZER)
public class InitializeController {
    static final String INITIALIZER_CONTROLLER = "initializer-controller";
    static final String API_VERSION = "v1";
    static final String API_PREFIX = "/api/" + API_VERSION;
    static final String API_INITIALIZER = API_PREFIX + "/initializer";

    private final DatabaseBootstrap databaseBootstrap;


    @PostMapping
    @Operation(
            summary = "Заполнить записями все таблицы базы данных",
            tags = {INITIALIZER_CONTROLLER}
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void init() {
        databaseBootstrap.initialize();
    }
}
