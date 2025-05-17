package dev.invest.db.repository;

import dev.invest.db.jooq.org.jooq.generated.invest.tables.Share;
import dev.invest.db.jooq.org.jooq.generated.invest.tables.records.ShareRecord;
import dev.invest.utils.BrandUtils;
import dev.invest.utils.DateUtils;
import dev.invest.utils.MoneyUtils;
import static java.lang.Math.abs;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ShareRepository {
    private final DSLContext dslContext;
    private final Random random = new Random();

    public int save(ru.tinkoff.piapi.contract.v1.Share share) {
        return dslContext.insertInto(Share.SHARE,
                        Share.SHARE.FIGI,
                        Share.SHARE.TICKER,
                        Share.SHARE.CLASS_CODE,
                        Share.SHARE.ISIN,
                        Share.SHARE.LOT,
                        Share.SHARE.CURRENCY,
                        Share.SHARE.KLONG,
                        Share.SHARE.KSHORT,
                        Share.SHARE.DLONG,
                        Share.SHARE.DSHORT,
                        Share.SHARE.DLONG_MIN,
                        Share.SHARE.DSHORT_MIN,
                        Share.SHARE.SHORT_ENABLED_FLAG,
                        Share.SHARE.NAME,
                        Share.SHARE.EXCHANGE,
                        Share.SHARE.IPO_DATE,
                        Share.SHARE.ISSUE_SIZE,
                        Share.SHARE.COUNTRY_OF_RISK,
                        Share.SHARE.COUNTRY_OF_RISK_NAME,
                        Share.SHARE.SECTOR,
                        Share.SHARE.ISSUE_SIZE_PLAN,
                        Share.SHARE.NOMINAL,
                        Share.SHARE.TRADING_STATUS,
                        Share.SHARE.OTC_FLAG,
                        Share.SHARE.BUY_AVAILABLE_FLAG,
                        Share.SHARE.SELL_AVAILABLE_FLAG,
                        Share.SHARE.DIV_YIELD_FLAG,
                        Share.SHARE.SHARE_TYPE,
                        Share.SHARE.MIN_PRICE_INCREMENT,
                        Share.SHARE.API_TRADE_AVAILABLE_FLAG,
                        Share.SHARE.UID,
                        Share.SHARE.REAL_EXCHANGE,
                        Share.SHARE.POSITION_UID,
                        Share.SHARE.ASSET_UID,
                        Share.SHARE.INSTRUMENT_EXCHANGE,
                        Share.SHARE.FOR_IIS_FLAG,
                        Share.SHARE.FOR_QUAL_INVESTOR_FLAG,
                        Share.SHARE.WEEKEND_FLAG,
                        Share.SHARE.BLOCKED_TCA_FLAG,
                        Share.SHARE.LIQUIDITY_FLAG,
                        Share.SHARE.FIRST_1MIN_CANDLE_DATE,
                        Share.SHARE.FIRST_1DAY_CANDLE_DATE,
                        Share.SHARE.BRAND,
                        Share.SHARE.DLONG_CLIENT,
                        Share.SHARE.DSHORT_CLIENT,
                        Share.SHARE.DAILY_CHANGE,
                        Share.SHARE.VOLUME_SHARE,
                        Share.SHARE.MARKET_CAPITALIZATION
                )
                .values(share.getFigi(),
                        share.getTicker(),
                        share.getClassCode(),
                        share.getIsin(),
                        share.getLot(),
                        share.getCurrency(),
                        MoneyUtils.toBigDecimal(share.getKlong()),
                        MoneyUtils.toBigDecimal(share.getKshort()),
                        MoneyUtils.toBigDecimal(share.getDlong()),
                        MoneyUtils.toBigDecimal(share.getDshort()),
                        MoneyUtils.toBigDecimal(share.getDlongMin()),
                        MoneyUtils.toBigDecimal(share.getDshortMin()),
                        share.getShortEnabledFlag(),
                        share.getName(),
                        share.getExchange(),
                        DateUtils.toSqlTimestamp(share.getIpoDate()),
                        share.getIssueSize(),
                        share.getCountryOfRisk(),
                        share.getCountryOfRiskName(),
                        share.getSector(),
                        share.getIssueSizePlan(),
                        BigDecimal.valueOf(abs(random.nextDouble()* 1000)),
                        share.getTradingStatus(),
                        share.getOtcFlag(),
                        share.getBuyAvailableFlag(),
                        share.getSellAvailableFlag(),
                        share.getDivYieldFlag(),
                        share.getShareType(),
                        MoneyUtils.toBigDecimal(share.getMinPriceIncrement()),
                        share.getApiTradeAvailableFlag(),
                        share.getUid(),
                        share.getRealExchange(),
                        share.getPositionUid(),
                        share.getAssetUid(),
                        share.getInstrumentExchange(),
                        share.getForIisFlag(),
                        share.getForQualInvestorFlag(),
                        share.getWeekendFlag(),
                        share.getBlockedTcaFlag(),
                        share.getLiquidityFlag(),
                        DateUtils.toSqlTimestamp(share.getFirst1MinCandleDate()),
                        DateUtils.toSqlTimestamp(share.getFirst1DayCandleDate()),
                        BrandUtils.toString(share.getBrand()),
                        MoneyUtils.toBigDecimal(share.getDlongClient()),
                        MoneyUtils.toBigDecimal(share.getDshortClient()),
                        BigDecimal.valueOf(abs(random.nextDouble() * 10)),
                        BigDecimal.valueOf(abs(random.nextDouble()* 1000)),
                        BigDecimal.valueOf(abs(random.nextDouble() * 10000))
                )
                .onDuplicateKeyIgnore()
                .execute();
    }

    public List<ShareRecord> getAllOffsetLimit(int page, int size) {
        return dslContext.selectFrom(Share.SHARE)
                .orderBy(Share.SHARE.FIGI.asc())
                .limit(size)
                .offset(page * size)
                .fetch();
    }

    public List<ShareRecord> getAll() {
        return dslContext.selectFrom(Share.SHARE)
                .orderBy(Share.SHARE.FIGI.asc())
                .fetch();
    }

    public Optional<ShareRecord> getById(UUID uid) {
        return dslContext.selectFrom(Share.SHARE)
                .where(Share.SHARE.UID.eq(uid))
                .fetchOptional();
    }

    public int update(ru.tinkoff.piapi.contract.v1.Share share) {
        return dslContext.update(Share.SHARE)
                .set(Share.SHARE.FIGI, share.getFigi())
                .set(Share.SHARE.TICKER, share.getTicker())
                .set(Share.SHARE.CLASS_CODE, share.getClassCode())
                .set(Share.SHARE.ISIN, share.getIsin())
                .set(Share.SHARE.LOT, share.getLot())
                .set(Share.SHARE.CURRENCY, share.getCurrency())
                .set(Share.SHARE.KLONG, MoneyUtils.toBigInteger(share.getKlong()))
                .set(Share.SHARE.KSHORT, MoneyUtils.toBigInteger(share.getKshort()))
                .set(Share.SHARE.DLONG, MoneyUtils.toBigInteger(share.getDlong()))
                .set(Share.SHARE.DSHORT, MoneyUtils.toBigInteger(share.getDshort()))
                .set(Share.SHARE.DLONG_MIN, MoneyUtils.toBigInteger(share.getDlongMin()))
                .set(Share.SHARE.DSHORT_MIN, MoneyUtils.toBigInteger(share.getDshortMin()))
                .set(Share.SHARE.SHORT_ENABLED_FLAG, share.getShortEnabledFlag())
                .set(Share.SHARE.NAME, share.getName())
                .set(Share.SHARE.EXCHANGE, share.getExchange())
                .set(Share.SHARE.IPO_DATE, DateUtils.toLocalDateTime(share.getIpoDate()))
                .set(Share.SHARE.ISSUE_SIZE, share.getIssueSize())
                .set(Share.SHARE.COUNTRY_OF_RISK, share.getCountryOfRisk())
                .set(Share.SHARE.COUNTRY_OF_RISK_NAME, share.getCountryOfRiskName())
                .set(Share.SHARE.SECTOR, share.getSector())
                .set(Share.SHARE.ISSUE_SIZE_PLAN, share.getIssueSizePlan())
                .set(Share.SHARE.NOMINAL, MoneyUtils.toBigInteger(share.getNominal()))
                .set(Share.SHARE.TRADING_STATUS, share.getTradingStatus().toString())
                .set(Share.SHARE.OTC_FLAG, share.getOtcFlag())
                .set(Share.SHARE.BUY_AVAILABLE_FLAG, share.getBuyAvailableFlag())
                .set(Share.SHARE.SELL_AVAILABLE_FLAG, share.getSellAvailableFlag())
                .set(Share.SHARE.DIV_YIELD_FLAG, share.getDivYieldFlag())
                .set(Share.SHARE.SHARE_TYPE, share.getShareType().toString())
                .set(Share.SHARE.MIN_PRICE_INCREMENT, MoneyUtils.toBigInteger(
                        share.getMinPriceIncrement()))
                .set(Share.SHARE.API_TRADE_AVAILABLE_FLAG, share.getApiTradeAvailableFlag())
                .set(Share.SHARE.REAL_EXCHANGE, share.getRealExchange().toString())
                .set(Share.SHARE.POSITION_UID, UUID.fromString(share.getPositionUid()))
                .set(Share.SHARE.ASSET_UID, UUID.fromString(share.getAssetUid()))
                .set(Share.SHARE.INSTRUMENT_EXCHANGE, share.getInstrumentExchange().toString())
                .set(Share.SHARE.FOR_IIS_FLAG, share.getForIisFlag())
                .set(Share.SHARE.FOR_QUAL_INVESTOR_FLAG, share.getForQualInvestorFlag())
                .set(Share.SHARE.WEEKEND_FLAG, share.getWeekendFlag())
                .set(Share.SHARE.BLOCKED_TCA_FLAG, share.getBlockedTcaFlag())
                .set(Share.SHARE.LIQUIDITY_FLAG, share.getLiquidityFlag())
                .set(Share.SHARE.FIRST_1MIN_CANDLE_DATE, DateUtils.toLocalDateTime(
                        share.getFirst1MinCandleDate()))
                .set(Share.SHARE.FIRST_1DAY_CANDLE_DATE, DateUtils.toLocalDateTime(
                        share.getFirst1DayCandleDate()))
                .set(Share.SHARE.BRAND, BrandUtils.toString(share.getBrand()))
                .set(Share.SHARE.DLONG_CLIENT, MoneyUtils.toBigInteger(share.getDlongClient()))
                .set(Share.SHARE.DSHORT_CLIENT, MoneyUtils.toBigInteger(share.getDshortClient()))
                .where(Share.SHARE.UID.eq(UUID.fromString(share.getUid())))
                .execute();
    }

    public Optional<ShareRecord> delete(UUID uid) {
        return dslContext.deleteFrom(Share.SHARE)
                .where(Share.SHARE.UID.eq(uid))
                .returning().fetchOptional();
    }
}
