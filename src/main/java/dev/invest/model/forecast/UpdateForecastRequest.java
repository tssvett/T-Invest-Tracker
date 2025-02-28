package dev.invest.model.forecast;

import java.math.BigDecimal;

public record UpdateForecastRequest(
        Integer recommendation,
        BigDecimal current_price,
        BigDecimal consensus,
        BigDecimal min_target,
        BigDecimal max_target,
        BigDecimal price_change,
        BigDecimal price_change_rel
) {
}