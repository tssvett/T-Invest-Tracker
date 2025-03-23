package dev.invest.model.forecast;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.UUID;

// https://russianinvestments.github.io/investAPI/instruments/#getforecastresponseconsensusitem
public record ForecastDto(
        UUID id,
        @NotBlank String currency,
        @NotBlank String ticker,
        String recommendation,
        BigDecimal currentPrice,
        BigDecimal consensus,
        BigDecimal minTarget,
        BigDecimal maxTarget,
        BigDecimal priceChange,
        BigDecimal priceChangeRel
) {
}
