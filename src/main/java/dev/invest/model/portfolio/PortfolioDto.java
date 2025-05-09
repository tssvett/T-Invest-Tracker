package dev.invest.model.portfolio;

import java.util.UUID;

public record PortfolioDto(
        UUID userId,
        UUID shareId
) {
}
