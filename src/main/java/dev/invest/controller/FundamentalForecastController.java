package dev.invest.controller;

import dev.invest.model.fundamental.CreateFundamentalRequest;
import dev.invest.model.fundamental.FundamentalDto;
import dev.invest.model.fundamental.UpdateFundamentalRequest;
import dev.invest.service.FundamentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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
@Validated
@RestController
@RequiredArgsConstructor
@Tag(
        name = FundamentalForecastController.FUNDAMENTAL_FORECAST_CONTROLLER,
        description = "API Фундаментальных прогнозов по акциям"
)
@SecurityRequirement(name = "JWT")
@RequestMapping(FundamentalForecastController.API_FUNDAMENTAL_FORECAST)
public class FundamentalForecastController {
    static final String FUNDAMENTAL_FORECAST_CONTROLLER = "fundamental-controller";
    static final String API_VERSION = "v1";
    static final String API_PREFIX = "/api/" + API_VERSION;
    static final String API_FUNDAMENTAL_FORECAST = API_PREFIX + "/fundamental";

    private final FundamentalService fundamentalService;

    @GetMapping
    @Operation(
            summary = "Получить все фундаментальные прогнозы по акциям",
            tags = {FUNDAMENTAL_FORECAST_CONTROLLER}
    )
    public List<FundamentalDto> findAllFundamentalForecasts() {
        return fundamentalService.getAll();
    }

    @GetMapping("/{uuid}")
    @Operation(
            summary = "Получить фундаментальный прогноз по его идентификатору",
            tags = {FUNDAMENTAL_FORECAST_CONTROLLER}
    )
    public FundamentalDto findFundamentalForecastByUid(@PathVariable UUID uuid) {
        return fundamentalService.getByUid(uuid);
    }

    @PostMapping
    @Operation(
            summary = "Создать фундаметнальный прогноз по акциям",
            tags = {FUNDAMENTAL_FORECAST_CONTROLLER}
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    public FundamentalDto createFundamentalForecast(@RequestBody @Valid CreateFundamentalRequest request) {
        return fundamentalService.create(request);
    }

    @PutMapping("/{uuid}")
    @Operation(
            summary = "Обновить фундаментальный прогноз по идентификатору",
            tags = {FUNDAMENTAL_FORECAST_CONTROLLER}
    )
    public FundamentalDto updateFundamentalForecast(@RequestBody @Valid UpdateFundamentalRequest request,
                                                    @PathVariable UUID uuid) {
        return fundamentalService.update(uuid, request);
    }

    @DeleteMapping("/{uuid}")
    @Operation(
            summary = "Удалить фундаментальный прогноз по идентификатору",
            tags = {FUNDAMENTAL_FORECAST_CONTROLLER}
    )
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteFundamentalForecast(@PathVariable UUID uuid) {
        fundamentalService.deleteByUid(uuid);
    }
}
