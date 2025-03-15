package dev.invest.model.forecast;

import java.math.BigDecimal;

public record UpdateForecastRequest(
        Integer recommendation,
        BigDecimal currentPrice,
        BigDecimal consensus,
        BigDecimal minTarget,
        BigDecimal maxTarget,
        BigDecimal priceChange,
        BigDecimal priceChangeRel
) {
}
