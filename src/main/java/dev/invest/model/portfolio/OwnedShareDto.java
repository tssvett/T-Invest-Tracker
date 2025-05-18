package dev.invest.model.portfolio;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

public record OwnedShareDto(
        BigInteger id,
        UUID userId,
        UUID shareId,
        BigDecimal shareCount,
        BigDecimal sharePrice
) {
}
