package dev.invest.model.forecast;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

public record CreateForecastRequest(
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