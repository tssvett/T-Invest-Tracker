package dev.invest.utils;

import dev.invest.db.jooq.org.jooq.generated.invest.tables.records.UsersShareRecord;
import dev.invest.model.user.CreateUserRequest;
import java.math.BigInteger;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;
import ru.tinkoff.piapi.contract.v1.GetAssetFundamentalsResponse;
import ru.tinkoff.piapi.contract.v1.GetForecastResponse;
import ru.tinkoff.piapi.contract.v1.Quotation;
import ru.tinkoff.piapi.contract.v1.Recommendation;


@UtilityClass
public class GenerateUtils {
    private static final Random random = new Random();

    public static Quotation buildRandomQuotation() {
        return Quotation.newBuilder()
                .setUnits(random.nextInt(1, 10000))
                .setNano(random.nextInt(1, 10000))
                .build();
    }

    public static GetForecastResponse.ConsensusItem buildRandomConsensusItem(List<String> tickers) {
        return GetForecastResponse.ConsensusItem.newBuilder()
                .setUid(UUID.randomUUID().toString())
                .setTicker(tickers.get(random.nextInt(1, 10000) % tickers.size()))
                .setRecommendation(Recommendation.RECOMMENDATION_BUY)
                .setCurrency("RUB")
                .setCurrentPrice(buildRandomQuotation())
                .setConsensus(buildRandomQuotation())
                .setMinTarget(buildRandomQuotation())
                .setMaxTarget(buildRandomQuotation())
                .setPriceChange(buildRandomQuotation())
                .setPriceChangeRel(buildRandomQuotation())
                .build();
    }

    public static List<GetForecastResponse.ConsensusItem> buildConsensusList(List<String> tickers) {
        return List.of(
                buildRandomConsensusItem(tickers),
                buildRandomConsensusItem(tickers),
                buildRandomConsensusItem(tickers)
        );
    }

    public static GetAssetFundamentalsResponse.StatisticResponse buildRandomFundamental(List<UUID> assetUuids) {
        return GetAssetFundamentalsResponse.StatisticResponse.newBuilder()
                .setAssetUid(assetUuids.get(random.nextInt(0, assetUuids.size())).toString())
                .setCurrency("USD")
                .setMarketCapitalization(random.nextDouble(1, 10000))
                .setHighPriceLast52Weeks(random.nextDouble(1, 10000))
                .setLowPriceLast52Weeks(random.nextDouble(1, 10000))
                .setAverageDailyVolumeLast10Days(random.nextDouble(1, 10000))
                .setAverageDailyVolumeLast4Weeks(random.nextDouble(1, 10000))
                .setBeta(random.nextDouble(1, 10000))
                .setFreeFloat(random.nextDouble(1, 10000))
                .setForwardAnnualDividendYield(random.nextDouble(1, 10000))
                .setSharesOutstanding(random.nextDouble(1, 10000))
                .setRevenueTtm(random.nextDouble(1, 10000))
                .setEbitdaTtm(random.nextDouble(1, 10000))
                .setNetIncomeTtm(random.nextDouble(1, 10000))
                .setEpsTtm(random.nextDouble(1, 10000))
                .setDilutedEpsTtm(random.nextDouble(1, 10000))
                .setFreeCashFlowTtm(random.nextDouble(1, 10000))
                .setFiveYearAnnualRevenueGrowthRate(random.nextDouble(1, 10000))
                .setThreeYearAnnualRevenueGrowthRate(random.nextDouble(1, 10000))
                .setPeRatioTtm(random.nextDouble(1, 10000))
                .setPriceToSalesTtm(random.nextDouble(1, 10000))
                .setPriceToBookTtm(random.nextDouble(1, 10000))
                .setPriceToFreeCashFlowTtm(random.nextDouble(1, 10000))
                .setTotalEnterpriseValueMrq(random.nextDouble(1, 10000))
                .setEvToEbitdaMrq(random.nextDouble(1, 10000))
                .setNetMarginMrq(random.nextDouble(1, 10000))
                .setNetInterestMarginMrq(random.nextDouble(1, 10000))
                .setRoe(random.nextDouble(1, 10000))
                .setRoa(random.nextDouble(1, 10000))
                .setRoic(random.nextDouble(1, 10000))
                .setTotalDebtMrq(random.nextDouble(1, 10000))
                .setTotalDebtToEquityMrq(random.nextDouble(1, 10000))
                .setTotalDebtToEbitdaMrq(random.nextDouble(1, 10000))
                .setFreeCashFlowToPrice(random.nextDouble(1, 10000))
                .setNetDebtToEbitda(random.nextDouble(1, 10000))
                .setCurrentRatioMrq(random.nextDouble(1, 10000))
                .setFixedChargeCoverageRatioFy(random.nextDouble(1, 10000))
                .setDividendYieldDailyTtm(random.nextDouble(1, 10000))
                .setDividendRateTtm(random.nextDouble(1, 10000))
                .setDividendsPerShare(random.nextDouble(1, 10000))
                .setFiveYearsAverageDividendYield(random.nextDouble(1, 10000))
                .setFiveYearAnnualDividendGrowthRate(random.nextDouble(1, 10000))
                .setDividendPayoutRatioFy(random.nextDouble(1, 10000))
                .setBuyBackTtm(random.nextDouble(1, 10000))
                .setOneYearAnnualRevenueGrowthRate(random.nextDouble(1, 10000))
                .setDomicileIndicatorCode("US")
                .setAdrToCommonShareRatio(random.nextDouble(1, 10000))
                .setNumberOfEmployees(random.nextDouble(1, 10000))
                .setExDividendDate(DateUtils.getCurrentProtoTimestamp())
                .setFiscalPeriodStartDate(DateUtils.getCurrentProtoTimestamp())
                .setFiscalPeriodEndDate(DateUtils.getCurrentProtoTimestamp())
                .setRevenueChangeFiveYears(random.nextDouble(1, 10000))
                .setEpsChangeFiveYears(random.nextDouble(1, 10000))
                .setEbitdaChangeFiveYears(random.nextDouble(1, 10000))
                .setTotalDebtChangeFiveYears(random.nextDouble(1, 10000))
                .setEvToSales(random.nextDouble(1, 10000))
                .build();
    }

    public static List<GetAssetFundamentalsResponse.StatisticResponse> buildFundamentalList(List<UUID> assetUuids) {
        return List.of(
                buildRandomFundamental(assetUuids),
                buildRandomFundamental(assetUuids),
                buildRandomFundamental(assetUuids)
        );
    }

    public static CreateUserRequest buildCreateUserRequest() {
        return new CreateUserRequest(
                RandomStringUtils.randomAlphabetic(20) + "@test.com",
                RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20));
    }

    public static List<CreateUserRequest> buildCreateUserRequestList() {
        return List.of(
                buildCreateUserRequest(),
                buildCreateUserRequest(),
                buildCreateUserRequest()
        );
    }

    public static UsersShareRecord buildUserShare(UUID userId, UUID shareUid) {
        return new UsersShareRecord(userId,
                shareUid,
                BigInteger.valueOf(random.nextLong(1, 1000)),
                BigInteger.valueOf(random.nextLong(1, 10000)),
                random.nextInt(1, 2000000)
        );
    }

    public static List<UsersShareRecord> buildUserShareList(List<UUID> userIds, List<UUID> shareIds) {
        return List.of(
                buildUserShare(userIds.get(0), shareIds.get(0)),
                buildUserShare(userIds.get(1), shareIds.get(1)),
                buildUserShare(userIds.get(2), shareIds.get(2))
        );
    }
}
