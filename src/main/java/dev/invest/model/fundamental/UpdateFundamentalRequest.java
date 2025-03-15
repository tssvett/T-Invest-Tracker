package dev.invest.model.fundamental;

import java.math.BigDecimal;

public record UpdateFundamentalRequest(
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
