package dev.invest.model.share;

import java.math.BigDecimal;
import java.util.UUID;

// https://russianinvestments.github.io/investAPI/instruments/#share
public record ShareDto(
    UUID uuid, // берем напрямую из api russianinvest
    String currency,
    String ticker,
    String name,
    String exchange,
    String sector,
    String country_of_risk_name,
    Boolean buy_available_flag,
    Boolean sell_available_flag,
    String figi,
    String share_type,
    BigDecimal dlong_client,
    BigDecimal dshort_client
) {
}
