package dev.invest.model.forecast;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

public record UpdateForecastRequest(
        @NotBlank String currency,
        @NotBlank String ticker,
        Integer recommendation,
        BigDecimal currentPrice,
        BigDecimal consensus,
        BigDecimal minTarget,
        BigDecimal maxTarget,
        BigDecimal priceChange,
        BigDecimal priceChangeRel
) {
}
