package dev.invest.db.repository;

import dev.invest.db.jooq.org.jooq.generated.invest.tables.Share;
import dev.invest.db.jooq.org.jooq.generated.invest.tables.records.ShareRecord;
import dev.invest.utils.BrandUtils;
import dev.invest.utils.DateUtils;
import dev.invest.utils.MoneyUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ShareRepository {
    private final DSLContext dslContext;

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
                        Share.SHARE.DSHORT_CLIENT
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
                        MoneyUtils.toBigDecimal(share.getNominal()),
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
                        MoneyUtils.toBigDecimal(share.getDshortClient())
                )
                .onDuplicateKeyIgnore()
                .execute();
    }

    public List<ShareRecord> getAll(int page, int size) {
        return dslContext.selectFrom(Share.SHARE)
                .orderBy(Share.SHARE.FIGI.asc())
                .limit(size)
                .offset(page * size)
                .fetch();
    }
}
