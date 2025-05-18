package dev.invest.model.share;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

/**
 * <a href="https://russianinvestments.github.io/investAPI/instruments/#share">Акция</a>
 */
public record ShareDto(
        UUID id,
        UUID assetId,
        @NotBlank String ticker,
        @NotBlank String currency,
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
        BigInteger nominal,
        BigDecimal dailyChange,
        BigDecimal volumeShare,
        BigDecimal marketCapitalization
) {
}
