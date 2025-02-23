package dev.invest.controller.forecast;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = ForecastController.FORECAST_CONTROLLER, description = "API Прогнозов по акциям")
public class ForecastController {

    static final String FORECAST_CONTROLLER = "forecast-controller";

    @GetMapping("/forecast")
    public String forecast() {
        return "forecast";
    }

}
