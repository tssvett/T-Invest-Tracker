package dev.invest.controller;

import dev.invest.model.forecast.CreateForecastRequest;
import dev.invest.model.forecast.ForecastDto;
import dev.invest.model.forecast.UpdateForecastRequest;
import dev.invest.service.ForecastService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
@RequestMapping(ForecastController.API_FORECAST)
@SecurityRequirement(name = "JWT")
@Tag(name = ForecastController.FORECAST_CONTROLLER, description = "API Прогнозов по акциям")
public class ForecastController {
    static final String FORECAST_CONTROLLER = "forecast-controller";
    static final String API_VERSION = "v1";
    static final String API_PREFIX = "/api/" + API_VERSION;
    static final String API_FORECAST = API_PREFIX + "/forecast";

    private final ForecastService forecastService;

    @GetMapping
    @Operation(
            summary = "Получить все прогнозы по акциям",
            tags = {FORECAST_CONTROLLER}
    )
    public List<ForecastDto> findAllForecasts() {
        return forecastService.getAll();
    }

    @GetMapping("/{uuid}")
    @Operation(
            summary = "Получить прогноз по его идентификатору",
            tags = {FORECAST_CONTROLLER}
    )
    public ForecastDto findForecastByUid(@NotNull @PathVariable UUID uuid) {
        return forecastService.getByUid(uuid);
    }

    @PostMapping
    @Operation(
            summary = "Создать прогноз по акциям",
            tags = {FORECAST_CONTROLLER}
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    public ForecastDto createForecast(@RequestBody @Valid CreateForecastRequest request) {
        return forecastService.create(request);
    }

    @PutMapping("/{uuid}")
    @Operation(
            summary = "Обновить прогноз по идентификатору",
            tags = {FORECAST_CONTROLLER}
    )
    public ForecastDto updateForecast(
            @RequestBody @Valid UpdateForecastRequest request,
            @NotNull @PathVariable UUID uuid
    ) {
        return forecastService.update(uuid, request);
    }

    @DeleteMapping("/{uuid}")
    @Operation(
            summary = "Удалить прогноз по идентификатору",
            tags = {FORECAST_CONTROLLER}
    )
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteForecast(@NotNull @PathVariable UUID uuid) {
        forecastService.deleteByUid(uuid);
    }
}
