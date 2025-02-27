package dev.invest.model.share;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.UUID;

public record CreateShareRequest(
        @NotBlank UUID uuid,

        @NotBlank String currency,
        @NotBlank String ticker,
        @NotBlank String name,
        @NotBlank String exchange,
        @NotBlank String sector,
        @NotBlank String country_of_risk_name,
        Boolean buy_available_flag,
        Boolean sell_available_flag,
        @NotBlank String figi,
        @NotBlank String share_type,
        BigDecimal dlong_client,
        BigDecimal dshort_client
) {
}
