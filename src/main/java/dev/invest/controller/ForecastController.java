package dev.invest.controller;

import dev.invest.model.forecast.CreateForecastRequest;
import dev.invest.model.forecast.ForecastDto;
import dev.invest.model.forecast.UpdateForecastRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@Tag(name = ForecastController.FORECAST_CONTROLLER, description = "API Прогнозов по акциям")
@RequestMapping(ForecastController.API_FORECAST)
public class ForecastController {
    static final String FORECAST_CONTROLLER = "forecast-controller";
    static final String API_VERSION = "v1";
    static final String API_PREFIX = "/api/" + API_VERSION;
    static final String API_FORECAST = API_PREFIX + "/forecast";

    @GetMapping
    @Operation(
            summary = "Получить все прогнозы по акциям",
            tags = {FORECAST_CONTROLLER}
    )
    public List<ForecastDto> findAllForecasts() {
        //TODO: Business logic
        return null;
    }

    @GetMapping("/{uuid}")
    @Operation(
            summary = "Получить прогноз по его идентификатору",
            tags = {FORECAST_CONTROLLER}
    )
    public ForecastDto findForecastByUid(@PathVariable UUID uuid) {
        //TODO: Business logic
        return null;
    }

    @PostMapping
    @Operation(
            summary = "Создать прогноз по акциям",
            tags = {FORECAST_CONTROLLER}
    )
    public ForecastDto createForecast(@RequestBody @Valid CreateForecastRequest request) {
        //TODO: Business logic
        return null;
    }

    @PutMapping("/{uuid}")
    @Operation(
            summary = "Обновить прогноз по идентификатору",
            tags = {FORECAST_CONTROLLER}
    )
    public ForecastDto updateForecast(@RequestBody @Valid UpdateForecastRequest request, @PathVariable UUID uuid) {
        //TODO: Business logic
        return null;
    }

    @DeleteMapping("/{uuid}")
    @Operation(
            summary = "Удалить прогноз по идентификатору",
            tags = {FORECAST_CONTROLLER}
    )
    public void deleteForecast(@PathVariable UUID uuid) {
        //TODO: Business logic
    }
}
