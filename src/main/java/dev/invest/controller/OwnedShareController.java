package dev.invest.controller;

import dev.invest.model.portfolio.OwnedShareCreateDto;
import dev.invest.model.portfolio.OwnedShareDto;
import dev.invest.model.share.ShareDto;
import dev.invest.model.user.UserDto;
import dev.invest.service.OwnedShareService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Tag(
        name = OwnedShareController.PORTFOLIO_CONTROLLER,
        description = "API Инвестиционного портфеля пользователя"
)
@SecurityRequirement(name = "JWT")
@RequestMapping(OwnedShareController.API_PORTFOLIO)
public class OwnedShareController {
    static final String PORTFOLIO_CONTROLLER = "portfolio-controller";
    static final String API_VERSION = "v1";
    static final String API_PREFIX = "/api/" + API_VERSION;
    static final String API_PORTFOLIO = API_PREFIX + "/portfolio";

    private final OwnedShareService ownedShareService;

    @GetMapping("share/{userId}")
    @Operation(
            summary = "Получить акции пользователя",
            tags = {PORTFOLIO_CONTROLLER}
    )
    @PreAuthorize("#userId == authentication.principal.userId or hasRole('ROLE_ADMIN')")
    public List<OwnedShareDto> findSharedByUser(@PathVariable UUID userId) {
        return ownedShareService.findSharesByUserId(userId);
    }

    @GetMapping("user/{shareUid}")
    @Operation(
            summary = "Получить пользователей с акцией",
            tags = {PORTFOLIO_CONTROLLER}
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserDto> findUsersByShare(@PathVariable UUID shareUid) {
        return ownedShareService.findUsersByShareId(shareUid);
    }

    @PostMapping
    @Operation(
            summary = "Добавить акцию пользователю",
            tags = {PORTFOLIO_CONTROLLER}
    )
    @PreAuthorize("#request.userId == authentication.principal.userId or hasRole('ROLE_ADMIN')")
    public OwnedShareDto addShareToUser(@RequestBody @Valid OwnedShareCreateDto request) {
        return ownedShareService.create(request);
    }

    @PutMapping
    @Operation(
            summary = "Обновить акцию пользователю",
            tags = {PORTFOLIO_CONTROLLER}
    )
    @PreAuthorize("#request.userId == authentication.principal.userId or hasRole('ROLE_ADMIN')")
    public OwnedShareDto updateShareToUser(@RequestBody @Valid OwnedShareDto request) {
        return ownedShareService.update(request);
    }

    @DeleteMapping
    @Operation(
            summary = "Удалить акцию пользователю",
            tags = {PORTFOLIO_CONTROLLER}
    )
    @PreAuthorize("#request.userId == authentication.principal.userId or hasRole('ROLE_ADMIN')")
    public void deleteShareToUser(@RequestBody @Valid OwnedShareDto request) {
        ownedShareService.delete(request);
    }
}
