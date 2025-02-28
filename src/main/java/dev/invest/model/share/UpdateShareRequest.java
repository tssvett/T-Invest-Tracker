package dev.invest.model.share;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.UUID;

public record UpdateShareRequest(
        UUID id,
        @NotBlank String name,
        @NotBlank String exchange,
        @NotBlank String sector,
        @NotBlank String country_of_risk_name,
        Boolean buy_available_flag,
        Boolean sell_available_flag,
        @NotBlank String share_type,
        BigDecimal dlong_client,
        BigDecimal dshort_client
) {
}