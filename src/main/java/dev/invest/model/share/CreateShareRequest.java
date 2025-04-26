package dev.invest.model.share;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

public record CreateShareRequest(
        UUID id,
        @NotBlank String currency,
        @NotBlank String ticker,
        @NotBlank String name,
        @NotBlank String exchange,
        @NotBlank String sector,
        @NotBlank String countryOfRiskName,
        Boolean buyAvailableFlag,
        Boolean sellAvailableFlag,
        @NotBlank String figi,
        @NotBlank String shareType,
        BigDecimal dlongClient,
        BigDecimal dshortClient,
        BigInteger nominal
) {
}
