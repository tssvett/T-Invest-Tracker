package dev.invest.db.repository;

import static dev.invest.db.jooq.org.jooq.generated.invest.Tables.FUNDAMENTAL_FORECAST;
import dev.invest.db.jooq.org.jooq.generated.invest.tables.records.FundamentalForecastRecord;
import dev.invest.model.fundamental.CreateFundamentalRequest;
import dev.invest.model.fundamental.UpdateFundamentalRequest;
import dev.invest.utils.DateUtils;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.tinkoff.piapi.contract.v1.GetAssetFundamentalsResponse;

@Slf4j
@Repository
@RequiredArgsConstructor
public class FundamentalRepository {
    private final DSLContext dslContext;

    public int save(GetAssetFundamentalsResponse.StatisticResponse fundamental) {
        if (fundamental == null) {
            log.warn("Попытка сохранить пустой фундаментальный прогноз. Пропускаем сущность...");
            return 0;
        }

        return dslContext.insertInto(FUNDAMENTAL_FORECAST,
                        FUNDAMENTAL_FORECAST.ASSET_UID,
                        FUNDAMENTAL_FORECAST.CURRENCY,
                        FUNDAMENTAL_FORECAST.MARKET_CAPITALIZATION,
                        FUNDAMENTAL_FORECAST.HIGH_PRICE_LAST_52_WEEKS,
                        FUNDAMENTAL_FORECAST.LOW_PRICE_LAST_52_WEEKS,
                        FUNDAMENTAL_FORECAST.AVERAGE_DAILY_VOLUME_LAST_10_DAYS,
                        FUNDAMENTAL_FORECAST.AVERAGE_DAILY_VOLUME_LAST_4_WEEKS,
                        FUNDAMENTAL_FORECAST.BETA,
                        FUNDAMENTAL_FORECAST.FREE_FLOAT,
                        FUNDAMENTAL_FORECAST.FORWARD_ANNUAL_DIVIDEND_YIELD,
                        FUNDAMENTAL_FORECAST.SHARES_OUTSTANDING,
                        FUNDAMENTAL_FORECAST.REVENUE_TTM,
                        FUNDAMENTAL_FORECAST.EBITDA_TTM,
                        FUNDAMENTAL_FORECAST.NET_INCOME_TTM,
                        FUNDAMENTAL_FORECAST.EPS_TTM,
                        FUNDAMENTAL_FORECAST.DILUTED_EPS_TTM,
                        FUNDAMENTAL_FORECAST.FREE_CASH_FLOW_TTM,
                        FUNDAMENTAL_FORECAST.FIVE_YEAR_ANNUAL_REVENUE_GROWTH_RATE,
                        FUNDAMENTAL_FORECAST.THREE_YEAR_ANNUAL_REVENUE_GROWTH_RATE,
                        FUNDAMENTAL_FORECAST.PE_RATIO_TTM,
                        FUNDAMENTAL_FORECAST.PRICE_TO_SALES_TTM,
                        FUNDAMENTAL_FORECAST.PRICE_TO_BOOK_TTM,
                        FUNDAMENTAL_FORECAST.PRICE_TO_FREE_CASH_FLOW_TTM,
                        FUNDAMENTAL_FORECAST.TOTAL_ENTERPRISE_VALUE_MRQ,
                        FUNDAMENTAL_FORECAST.EV_TO_EBITDA_MRQ,
                        FUNDAMENTAL_FORECAST.NET_MARGIN_MRQ,
                        FUNDAMENTAL_FORECAST.NET_INTEREST_MARGIN_MRQ,
                        FUNDAMENTAL_FORECAST.ROE,
                        FUNDAMENTAL_FORECAST.ROA,
                        FUNDAMENTAL_FORECAST.ROIC,
                        FUNDAMENTAL_FORECAST.TOTAL_DEBT_MRQ,
                        FUNDAMENTAL_FORECAST.TOTAL_DEBT_TO_EQUITY_MRQ,
                        FUNDAMENTAL_FORECAST.TOTAL_DEBT_TO_EBITDA_MRQ,
                        FUNDAMENTAL_FORECAST.FREE_CASH_FLOW_TO_PRICE,
                        FUNDAMENTAL_FORECAST.NET_DEBT_TO_EBITDA,
                        FUNDAMENTAL_FORECAST.CURRENT_RATIO_MRQ,
                        FUNDAMENTAL_FORECAST.FIXED_CHARGE_COVERAGE_RATIO_FY,
                        FUNDAMENTAL_FORECAST.DIVIDEND_YIELD_DAILY_TTM,
                        FUNDAMENTAL_FORECAST.DIVIDEND_RATE_TTM,
                        FUNDAMENTAL_FORECAST.DIVIDENDS_PER_SHARE,
                        FUNDAMENTAL_FORECAST.FIVE_YEARS_AVERAGE_DIVIDEND_YIELD,
                        FUNDAMENTAL_FORECAST.FIVE_YEAR_ANNUAL_DIVIDEND_GROWTH_RATE,
                        FUNDAMENTAL_FORECAST.DIVIDEND_PAYOUT_RATIO_FY,
                        FUNDAMENTAL_FORECAST.BUY_BACK_TTM,
                        FUNDAMENTAL_FORECAST.ONE_YEAR_ANNUAL_REVENUE_GROWTH_RATE,
                        FUNDAMENTAL_FORECAST.DOMICILE_INDICATOR_CODE,
                        FUNDAMENTAL_FORECAST.ADR_TO_COMMON_SHARE_RATIO,
                        FUNDAMENTAL_FORECAST.NUMBER_OF_EMPLOYEES,
                        FUNDAMENTAL_FORECAST.EX_DIVIDEND_DATE,
                        FUNDAMENTAL_FORECAST.FISCAL_PERIOD_START_DATE,
                        FUNDAMENTAL_FORECAST.FISCAL_PERIOD_END_DATE,
                        FUNDAMENTAL_FORECAST.REVENUE_CHANGE_FIVE_YEARS,
                        FUNDAMENTAL_FORECAST.EPS_CHANGE_FIVE_YEARS,
                        FUNDAMENTAL_FORECAST.EBITDA_CHANGE_FIVE_YEARS,
                        FUNDAMENTAL_FORECAST.TOTAL_DEBT_CHANGE_FIVE_YEARS,
                        FUNDAMENTAL_FORECAST.EV_TO_SALES)
                .values(
                        fundamental.getAssetUid(),
                        fundamental.getCurrency(),
                        fundamental.getMarketCapitalization(),
                        fundamental.getHighPriceLast52Weeks(),
                        fundamental.getLowPriceLast52Weeks(),
                        fundamental.getAverageDailyVolumeLast10Days(),
                        fundamental.getAverageDailyVolumeLast4Weeks(),
                        fundamental.getBeta(),
                        fundamental.getFreeFloat(),
                        fundamental.getForwardAnnualDividendYield(),
                        fundamental.getSharesOutstanding(),
                        fundamental.getRevenueTtm(),
                        fundamental.getEbitdaTtm(),
                        fundamental.getNetIncomeTtm(),
                        fundamental.getEpsTtm(),
                        fundamental.getDilutedEpsTtm(),
                        fundamental.getFreeCashFlowTtm(),
                        fundamental.getFiveYearAnnualRevenueGrowthRate(),
                        fundamental.getThreeYearAnnualRevenueGrowthRate(),
                        fundamental.getPeRatioTtm(),
                        fundamental.getPriceToSalesTtm(),
                        fundamental.getPriceToBookTtm(),
                        fundamental.getPriceToFreeCashFlowTtm(),
                        fundamental.getTotalEnterpriseValueMrq(),
                        fundamental.getEvToEbitdaMrq(),
                        fundamental.getNetMarginMrq(),
                        fundamental.getNetInterestMarginMrq(),
                        fundamental.getRoe(),
                        fundamental.getRoa(),
                        fundamental.getRoic(),
                        fundamental.getTotalDebtMrq(),
                        fundamental.getTotalDebtToEquityMrq(),
                        fundamental.getTotalDebtToEbitdaMrq(),
                        fundamental.getFreeCashFlowToPrice(),
                        fundamental.getNetDebtToEbitda(),
                        fundamental.getCurrentRatioMrq(),
                        fundamental.getFixedChargeCoverageRatioFy(),
                        fundamental.getDividendYieldDailyTtm(),
                        fundamental.getDividendRateTtm(),
                        fundamental.getDividendsPerShare(),
                        fundamental.getFiveYearsAverageDividendYield(),
                        fundamental.getFiveYearAnnualDividendGrowthRate(),
                        fundamental.getDividendPayoutRatioFy(),
                        fundamental.getBuyBackTtm(),
                        fundamental.getOneYearAnnualRevenueGrowthRate(),
                        fundamental.getDomicileIndicatorCode(),
                        fundamental.getAdrToCommonShareRatio(),
                        fundamental.getNumberOfEmployees(),
                        DateUtils.toSqlTimestamp(fundamental.getExDividendDate()),
                        DateUtils.toSqlTimestamp(fundamental.getFiscalPeriodStartDate()),
                        DateUtils.toSqlTimestamp(fundamental.getFiscalPeriodEndDate()),
                        fundamental.getRevenueChangeFiveYears(),
                        fundamental.getEpsChangeFiveYears(),
                        fundamental.getEbitdaChangeFiveYears(),
                        fundamental.getTotalDebtChangeFiveYears(),
                        fundamental.getEvToSales())
                .onDuplicateKeyIgnore()
                .execute();
    }

    public List<FundamentalForecastRecord> getAll() {
        return dslContext.selectFrom(FUNDAMENTAL_FORECAST)
                .fetch();
    }

    public Optional<FundamentalForecastRecord> getById(UUID assetUid) {
        return dslContext.selectFrom(FUNDAMENTAL_FORECAST)
                .where(FUNDAMENTAL_FORECAST.ASSET_UID.eq(assetUid))
                .fetchOptional();
    }

    public int update(GetAssetFundamentalsResponse.StatisticResponse fundamental) {
        if (fundamental == null) {
            log.warn("Попытка обновить пустой фундаментальный прогноз. Пропускаем сущность...");
            return 0;
        }

        return dslContext.update(FUNDAMENTAL_FORECAST)
                .set(FUNDAMENTAL_FORECAST.CURRENCY, fundamental.getCurrency())
                .set(FUNDAMENTAL_FORECAST.MARKET_CAPITALIZATION, fundamental.getMarketCapitalization())
                .set(FUNDAMENTAL_FORECAST.HIGH_PRICE_LAST_52_WEEKS, fundamental.getHighPriceLast52Weeks())
                .set(FUNDAMENTAL_FORECAST.LOW_PRICE_LAST_52_WEEKS, fundamental.getLowPriceLast52Weeks())
                .set(FUNDAMENTAL_FORECAST.AVERAGE_DAILY_VOLUME_LAST_10_DAYS,
                        fundamental.getAverageDailyVolumeLast10Days())
                .set(FUNDAMENTAL_FORECAST.AVERAGE_DAILY_VOLUME_LAST_4_WEEKS,
                        fundamental.getAverageDailyVolumeLast4Weeks())
                .set(FUNDAMENTAL_FORECAST.BETA, fundamental.getBeta())
                .set(FUNDAMENTAL_FORECAST.FREE_FLOAT, fundamental.getFreeFloat())
                .set(FUNDAMENTAL_FORECAST.FORWARD_ANNUAL_DIVIDEND_YIELD,
                        fundamental.getForwardAnnualDividendYield())
                .set(FUNDAMENTAL_FORECAST.SHARES_OUTSTANDING, fundamental.getSharesOutstanding())
                .set(FUNDAMENTAL_FORECAST.REVENUE_TTM, fundamental.getRevenueTtm())
                .set(FUNDAMENTAL_FORECAST.EBITDA_TTM, fundamental.getEbitdaTtm())
                .set(FUNDAMENTAL_FORECAST.NET_INCOME_TTM, fundamental.getNetIncomeTtm())
                .set(FUNDAMENTAL_FORECAST.EPS_TTM, fundamental.getEpsTtm())
                .set(FUNDAMENTAL_FORECAST.DILUTED_EPS_TTM, fundamental.getDilutedEpsTtm())
                .set(FUNDAMENTAL_FORECAST.FREE_CASH_FLOW_TTM, fundamental.getFreeCashFlowTtm())
                .set(FUNDAMENTAL_FORECAST.FIVE_YEAR_ANNUAL_REVENUE_GROWTH_RATE,
                        fundamental.getFiveYearAnnualRevenueGrowthRate())
                .set(FUNDAMENTAL_FORECAST.THREE_YEAR_ANNUAL_REVENUE_GROWTH_RATE,
                        fundamental.getThreeYearAnnualRevenueGrowthRate())
                .set(FUNDAMENTAL_FORECAST.PE_RATIO_TTM, fundamental.getPeRatioTtm())
                .set(FUNDAMENTAL_FORECAST.PRICE_TO_SALES_TTM, fundamental.getPriceToSalesTtm())
                .set(FUNDAMENTAL_FORECAST.PRICE_TO_BOOK_TTM, fundamental.getPriceToBookTtm())
                .set(FUNDAMENTAL_FORECAST.PRICE_TO_FREE_CASH_FLOW_TTM,
                        fundamental.getPriceToFreeCashFlowTtm())
                .set(FUNDAMENTAL_FORECAST.TOTAL_ENTERPRISE_VALUE_MRQ,
                        fundamental.getTotalEnterpriseValueMrq())
                .set(FUNDAMENTAL_FORECAST.EV_TO_EBITDA_MRQ, fundamental.getEvToEbitdaMrq())
                .set(FUNDAMENTAL_FORECAST.NET_MARGIN_MRQ, fundamental.getNetMarginMrq())
                .set(FUNDAMENTAL_FORECAST.NET_INTEREST_MARGIN_MRQ, fundamental.getNetInterestMarginMrq())
                .set(FUNDAMENTAL_FORECAST.ROE, fundamental.getRoe())
                .set(FUNDAMENTAL_FORECAST.ROA, fundamental.getRoa())
                .set(FUNDAMENTAL_FORECAST.ROIC, fundamental.getRoic())
                .set(FUNDAMENTAL_FORECAST.TOTAL_DEBT_MRQ, fundamental.getTotalDebtMrq())
                .set(FUNDAMENTAL_FORECAST.TOTAL_DEBT_TO_EQUITY_MRQ, fundamental.getTotalDebtToEquityMrq())
                .set(FUNDAMENTAL_FORECAST.TOTAL_DEBT_TO_EBITDA_MRQ, fundamental.getTotalDebtToEbitdaMrq())
                .set(FUNDAMENTAL_FORECAST.FREE_CASH_FLOW_TO_PRICE, fundamental.getFreeCashFlowToPrice())
                .set(FUNDAMENTAL_FORECAST.NET_DEBT_TO_EBITDA, fundamental.getNetDebtToEbitda())
                .set(FUNDAMENTAL_FORECAST.CURRENT_RATIO_MRQ, fundamental.getCurrentRatioMrq())
                .set(FUNDAMENTAL_FORECAST.FIXED_CHARGE_COVERAGE_RATIO_FY,
                        fundamental.getFixedChargeCoverageRatioFy())
                .set(FUNDAMENTAL_FORECAST.DIVIDEND_YIELD_DAILY_TTM,
                        fundamental.getDividendYieldDailyTtm())
                .set(FUNDAMENTAL_FORECAST.DIVIDEND_RATE_TTM, fundamental.getDividendRateTtm())
                .set(FUNDAMENTAL_FORECAST.DIVIDENDS_PER_SHARE, fundamental.getDividendsPerShare())
                .set(FUNDAMENTAL_FORECAST.FIVE_YEARS_AVERAGE_DIVIDEND_YIELD,
                        fundamental.getFiveYearsAverageDividendYield())
                .set(FUNDAMENTAL_FORECAST.FIVE_YEAR_ANNUAL_DIVIDEND_GROWTH_RATE,
                        fundamental.getFiveYearAnnualDividendGrowthRate())
                .set(FUNDAMENTAL_FORECAST.DIVIDEND_PAYOUT_RATIO_FY, fundamental.getDividendPayoutRatioFy())
                .set(FUNDAMENTAL_FORECAST.BUY_BACK_TTM, fundamental.getBuyBackTtm())
                .set(FUNDAMENTAL_FORECAST.ONE_YEAR_ANNUAL_REVENUE_GROWTH_RATE,
                        fundamental.getOneYearAnnualRevenueGrowthRate())
                .set(FUNDAMENTAL_FORECAST.DOMICILE_INDICATOR_CODE,
                        fundamental.getDomicileIndicatorCode())
                .set(FUNDAMENTAL_FORECAST.ADR_TO_COMMON_SHARE_RATIO,
                        fundamental.getAdrToCommonShareRatio())
                .set(FUNDAMENTAL_FORECAST.NUMBER_OF_EMPLOYEES, fundamental.getNumberOfEmployees())
                .set(FUNDAMENTAL_FORECAST.EX_DIVIDEND_DATE,
                        DateUtils.toLocalDateTime(fundamental.getExDividendDate()))
                .set(FUNDAMENTAL_FORECAST.FISCAL_PERIOD_START_DATE,
                        DateUtils.toLocalDateTime(fundamental.getFiscalPeriodStartDate()))
                .set(FUNDAMENTAL_FORECAST.FISCAL_PERIOD_END_DATE,
                        DateUtils.toLocalDateTime(fundamental.getFiscalPeriodEndDate()))
                .set(FUNDAMENTAL_FORECAST.REVENUE_CHANGE_FIVE_YEARS,
                        fundamental.getRevenueChangeFiveYears())
                .set(FUNDAMENTAL_FORECAST.EPS_CHANGE_FIVE_YEARS,
                        fundamental.getEpsChangeFiveYears())
                .set(FUNDAMENTAL_FORECAST.EBITDA_CHANGE_FIVE_YEARS,
                        fundamental.getEbitdaChangeFiveYears())
                .set(FUNDAMENTAL_FORECAST.TOTAL_DEBT_CHANGE_FIVE_YEARS,
                        fundamental.getTotalDebtChangeFiveYears())
                .set(FUNDAMENTAL_FORECAST.EV_TO_SALES, fundamental.getEvToSales())
                .where(FUNDAMENTAL_FORECAST.ASSET_UID.eq(UUID.fromString(fundamental.getAssetUid())))
                .execute();
    }

    public FundamentalForecastRecord save(CreateFundamentalRequest request) {
        return dslContext.insertInto(FUNDAMENTAL_FORECAST,
                        FUNDAMENTAL_FORECAST.ASSET_UID,
                        FUNDAMENTAL_FORECAST.CURRENCY,
                        FUNDAMENTAL_FORECAST.MARKET_CAPITALIZATION,
                        FUNDAMENTAL_FORECAST.HIGH_PRICE_LAST_52_WEEKS,
                        FUNDAMENTAL_FORECAST.LOW_PRICE_LAST_52_WEEKS,
                        FUNDAMENTAL_FORECAST.AVERAGE_DAILY_VOLUME_LAST_10_DAYS,
                        FUNDAMENTAL_FORECAST.AVERAGE_DAILY_VOLUME_LAST_4_WEEKS,
                        FUNDAMENTAL_FORECAST.REVENUE_TTM,
                        FUNDAMENTAL_FORECAST.FREE_CASH_FLOW_TTM,
                        FUNDAMENTAL_FORECAST.THREE_YEAR_ANNUAL_REVENUE_GROWTH_RATE,
                        FUNDAMENTAL_FORECAST.DIVIDEND_YIELD_DAILY_TTM,
                        FUNDAMENTAL_FORECAST.DIVIDEND_RATE_TTM,
                        FUNDAMENTAL_FORECAST.DIVIDENDS_PER_SHARE)
                .values(
                        request.assetId(),
                        request.currency(),
                        request.marketCapitalization().doubleValue(),
                        request.highPriceLast52Weeks().doubleValue(),
                        request.lowPriceLast52Weeks().doubleValue(),
                        request.averageDailyVolumeLast10Days().doubleValue(),
                        request.averageDailyVolumeLast4Weeks().doubleValue(),
                        request.revenueTtm().doubleValue(),
                        request.freeCashFlowTtm().doubleValue(),
                        request.threeYearAnnualRevenueGrowthRate().doubleValue(),
                        request.dividendYieldDailyTtm().doubleValue(),
                        request.dividendRateTtm().doubleValue(),
                        request.dividendsPerShare().doubleValue())
                .onDuplicateKeyIgnore()
                .returning().fetchOne();
    }

    public Optional<FundamentalForecastRecord> update(UUID assetUid, UpdateFundamentalRequest request) {
        if (request == null) {
            log.warn("Попытка обновить пустой фундаментальный прогноз. Пропускаем сущность...");
            return Optional.empty();
        }

        return dslContext.update(FUNDAMENTAL_FORECAST)
                .set(FUNDAMENTAL_FORECAST.CURRENCY, request.currency())
                .set(FUNDAMENTAL_FORECAST.MARKET_CAPITALIZATION, request.marketCapitalization().doubleValue())
                .set(FUNDAMENTAL_FORECAST.HIGH_PRICE_LAST_52_WEEKS, request.highPriceLast52Weeks().doubleValue())
                .set(FUNDAMENTAL_FORECAST.LOW_PRICE_LAST_52_WEEKS, request.lowPriceLast52Weeks().doubleValue())
                .set(FUNDAMENTAL_FORECAST.AVERAGE_DAILY_VOLUME_LAST_10_DAYS, request.averageDailyVolumeLast10Days().doubleValue())
                .set(FUNDAMENTAL_FORECAST.AVERAGE_DAILY_VOLUME_LAST_4_WEEKS, request.averageDailyVolumeLast4Weeks().doubleValue())
                .set(FUNDAMENTAL_FORECAST.REVENUE_TTM, request.revenueTtm().doubleValue())
                .set(FUNDAMENTAL_FORECAST.FREE_CASH_FLOW_TTM, request.freeCashFlowTtm().doubleValue())
                .set(FUNDAMENTAL_FORECAST.THREE_YEAR_ANNUAL_REVENUE_GROWTH_RATE, request.threeYearAnnualRevenueGrowthRate().doubleValue())
                .set(FUNDAMENTAL_FORECAST.DIVIDEND_YIELD_DAILY_TTM, request.dividendYieldDailyTtm().doubleValue())
                .set(FUNDAMENTAL_FORECAST.DIVIDEND_RATE_TTM, request.dividendRateTtm().doubleValue())
                .set(FUNDAMENTAL_FORECAST.DIVIDENDS_PER_SHARE, request.dividendsPerShare().doubleValue())
                .where(FUNDAMENTAL_FORECAST.ASSET_UID.eq(assetUid))
                .returning()
                .fetchOptional();
    }

    public Optional<FundamentalForecastRecord> delete(UUID assetUid) {
        return dslContext.deleteFrom(FUNDAMENTAL_FORECAST)
                .where(FUNDAMENTAL_FORECAST.ASSET_UID.eq(assetUid))
                .returning().fetchOptional();
    }
}
