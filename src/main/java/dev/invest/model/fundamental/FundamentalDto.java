package dev.invest.model.fundamental;

import java.math.BigDecimal;
import java.util.UUID;

// https://russianinvestments.github.io/investAPI/instruments/#getassetfundamentalsresponsestatisticresponse
public record FundamentalDto(
    UUID asset_uid,
    String currency,
    BigDecimal market_capitalization,
    BigDecimal high_price_last_52_weeks,
    BigDecimal low_price_last_52_weeks,
    BigDecimal average_daily_volume_last_10_days,
    BigDecimal average_daily_volume_last_4_weeks,
    BigDecimal revenue_ttm,
    BigDecimal free_cash_flow_ttm,
    BigDecimal three_year_annual_revenue_growth_rate,
    BigDecimal dividend_yield_daily_ttm,
    BigDecimal dividend_rate_ttm,
    BigDecimal dividends_per_share


) {
}
