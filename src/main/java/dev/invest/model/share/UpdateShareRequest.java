package dev.invest.model.share;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.UUID;

public record UpdateShareRequest(
        UUID id,
        @NotBlank String name,
        @NotBlank String exchange,
        @NotBlank String sector,
        @NotBlank String countryOfRiskName,
        Boolean buyAvailableFlag,
        Boolean sellAvailableFlag,
        @NotBlank String shareType,
        BigDecimal dlongClient,
        BigDecimal dshortClient
) {
}
