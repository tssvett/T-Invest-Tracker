package dev.invest.db.repository;

import static dev.invest.db.jooq.org.jooq.generated.invest.Tables.FUNDAMENTAL_FORECAST;
import dev.invest.db.jooq.org.jooq.generated.invest.tables.records.FundamentalForecastRecord;
import dev.invest.utils.DateUtils;
import java.util.List;
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
}
