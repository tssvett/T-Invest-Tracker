package dev.invest.controller;

import dev.invest.model.portfolio.PortfolioDto;
import dev.invest.model.share.ShareDto;
import dev.invest.model.user.UserDto;
import dev.invest.service.PortfolioService;
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
        name = PortfolioController.PORTFOLIO_CONTROLLER,
        description = "API Инвестиционного портфеля пользователя"
)
@SecurityRequirement(name = "JWT")
@RequestMapping(PortfolioController.API_PORTFOLIO)
public class PortfolioController {
    static final String PORTFOLIO_CONTROLLER = "portfolio-controller";
    static final String API_VERSION = "v1";
    static final String API_PREFIX = "/api/" + API_VERSION;
    static final String API_PORTFOLIO = API_PREFIX + "/portfolio";

    private final PortfolioService portfolioService;

    @GetMapping("share/{userId}")
    @Operation(
            summary = "Получить акции пользователя",
            tags = {PORTFOLIO_CONTROLLER}
    )
    @PreAuthorize("#userId == authentication.principal.userId or hasRole('ROLE_ADMIN')")
    public List<ShareDto> findSharedByUser(@PathVariable UUID userId) {
        return portfolioService.findSharesByUserId(userId);
    }

    @GetMapping("user/{shareUid}")
    @Operation(
            summary = "Получить пользователей с акцией",
            tags = {PORTFOLIO_CONTROLLER}
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserDto> findUsersByShare(@PathVariable UUID shareUid) {
        return portfolioService.findUsersByShareId(shareUid);
    }

    @PostMapping
    @Operation(
            summary = "Добавить акцию пользователю",
            tags = {PORTFOLIO_CONTROLLER}
    )
    @PreAuthorize("#userId == authentication.principal.userId or hasRole('ROLE_ADMIN')")
    public PortfolioDto addShareToUser(@RequestBody @Valid PortfolioDto request) {
        return portfolioService.create(request);
    }

    @PutMapping
    @Operation(
            summary = "Обновить акцию пользователю",
            tags = {PORTFOLIO_CONTROLLER}
    )
    @PreAuthorize("#request.userId == authentication.principal.userId or hasRole('ROLE_ADMIN')")
    public PortfolioDto updateShareToUser(@RequestBody @Valid PortfolioDto request) {
        return portfolioService.update(request);
    }

    @DeleteMapping
    @Operation(
            summary = "Удалить акцию пользователю",
            tags = {PORTFOLIO_CONTROLLER}
    )
    @PreAuthorize("#request.userId == authentication.principal.userId or hasRole('ROLE_ADMIN')")
    public void deleteShareToUser(@RequestBody @Valid PortfolioDto request) {
        portfolioService.delete(request);
    }
}
