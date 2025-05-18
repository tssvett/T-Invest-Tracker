package dev.invest.model.portfolio;

import java.math.BigDecimal;
import java.util.UUID;

public record OwnedShareCreateDto(
        UUID userId,
        UUID shareId,
        BigDecimal shareCount
) {

}