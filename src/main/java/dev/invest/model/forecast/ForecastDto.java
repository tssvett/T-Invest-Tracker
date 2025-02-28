package dev.invest.model.forecast;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.UUID;

// https://russianinvestments.github.io/investAPI/instruments/#getforecastresponseconsensusitem
public record ForecastDto(
        UUID id,
        @NotBlank String currency,
        @NotBlank String ticker,
        Integer recommendation,
        BigDecimal current_price,
        BigDecimal consensus,
        BigDecimal min_target,
        BigDecimal max_target,
        BigDecimal price_change,
        BigDecimal price_change_rel
) {

}