package dev.invest.model.fundamental;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.UUID;

// https://russianinvestments.github.io/investAPI/instruments/#getassetfundamentalsresponsestatisticresponse
public record FundamentalDto(
        UUID assetId,
        @NotBlank String currency,
        BigDecimal marketCapitalization,
        BigDecimal highPriceLast52Weeks,
        BigDecimal lowPriceLast52Weeks,
        BigDecimal averageDailyVolumeLast10Days,
        BigDecimal averageDailyVolumeLast4Weeks,
        BigDecimal revenueTtm,
        BigDecimal freeCashFlowTtm,
        BigDecimal threeYearAnnualRevenueGrowthRate,
        BigDecimal dividendYieldDailyTtm,
        BigDecimal dividendRateTtm,
        BigDecimal dividendsPerShare
) {
}
