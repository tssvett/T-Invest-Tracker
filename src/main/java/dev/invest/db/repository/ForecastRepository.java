package dev.invest.db.repository;

import static dev.invest.db.jooq.org.jooq.generated.invest.tables.Forecast.FORECAST;
import dev.invest.db.jooq.org.jooq.generated.invest.tables.records.ForecastRecord;
import dev.invest.model.forecast.CreateForecastRequest;
import dev.invest.model.forecast.UpdateForecastRequest;
import dev.invest.utils.MoneyUtils;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.tinkoff.piapi.contract.v1.GetForecastResponse.ConsensusItem;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ForecastRepository {
    private final DSLContext dslContext;

    public int save(ConsensusItem forecast) {
        if (forecast == null) {
            log.warn("Попытка сохранить пустой прогноз, не сохраняем");
            return 0;
        }
        return dslContext.insertInto(FORECAST,
                        FORECAST.UID,
                        FORECAST.TICKER,
                        FORECAST.RECOMENDATION,
                        FORECAST.CURRENCY,
                        FORECAST.CURRENT_PRICE,
                        FORECAST.CONSENSUS,
                        FORECAST.MIN_TARGET,
                        FORECAST.MAX_TARGET,
                        FORECAST.PRICE_CHANGE,
                        FORECAST.PRICE_CHANGE_REL)
                .values(
                        UUID.fromString(forecast.getUid()),
                        forecast.getTicker(),
                        forecast.getRecommendation().toString(),
                        forecast.getCurrency(),
                        MoneyUtils.toBigInteger(forecast.getCurrentPrice()),
                        MoneyUtils.toBigInteger(forecast.getConsensus()),
                        MoneyUtils.toBigInteger(forecast.getMinTarget()),
                        MoneyUtils.toBigInteger(forecast.getMaxTarget()),
                        MoneyUtils.toBigInteger(forecast.getPriceChange()),
                        MoneyUtils.toBigInteger(forecast.getPriceChangeRel())
                )
                .onDuplicateKeyIgnore()
                .execute();
    }

    public ForecastRecord save(CreateForecastRequest forecast) {
        if (forecast == null) {
            log.warn("Попытка сохранить пустой прогноз, не сохраняем");
            return null;
        }
        return dslContext.insertInto(FORECAST,
                        FORECAST.UID,
                        FORECAST.TICKER,
                        FORECAST.RECOMENDATION,
                        FORECAST.CURRENCY,
                        FORECAST.CURRENT_PRICE,
                        FORECAST.CONSENSUS,
                        FORECAST.MIN_TARGET,
                        FORECAST.MAX_TARGET,
                        FORECAST.PRICE_CHANGE,
                        FORECAST.PRICE_CHANGE_REL)
                .values(
                        UUID.randomUUID(),
                        forecast.ticker(),
                        forecast.recommendation().toString(),
                        forecast.currency(),
                        MoneyUtils.toBigInteger(forecast.currentPrice()),
                        MoneyUtils.toBigInteger(forecast.consensus()),
                        MoneyUtils.toBigInteger(forecast.minTarget()),
                        MoneyUtils.toBigInteger(forecast.maxTarget()),
                        MoneyUtils.toBigInteger(forecast.priceChange()),
                        MoneyUtils.toBigInteger(forecast.priceChangeRel())
                )
                .onDuplicateKeyIgnore()
                .returning().fetchAny();
    }

    public List<ForecastRecord> getAll() {
        return dslContext.selectFrom(FORECAST)
                .fetch();
    }

    public Optional<ForecastRecord> getById(UUID uid) {
        return Optional.ofNullable(dslContext.selectFrom(FORECAST)
                .where(FORECAST.UID.eq(uid))
                .fetchOne());
    }

    public int update(ConsensusItem forecast) {
        if (forecast == null) {
            log.warn("Попытка обновить пустой прогноз, не обновляем");
            return 0;
        }
        return dslContext.update(FORECAST)
                .set(FORECAST.TICKER, forecast.getTicker())
                .set(FORECAST.RECOMENDATION, forecast.getRecommendation().toString())
                .set(FORECAST.CURRENCY, forecast.getCurrency())
                .set(FORECAST.CURRENT_PRICE, MoneyUtils.toBigInteger(forecast.getCurrentPrice()))
                .set(FORECAST.CONSENSUS, MoneyUtils.toBigInteger(forecast.getConsensus()))
                .set(FORECAST.MIN_TARGET, MoneyUtils.toBigInteger(forecast.getMinTarget()))
                .set(FORECAST.MAX_TARGET, MoneyUtils.toBigInteger(forecast.getMaxTarget()))
                .set(FORECAST.PRICE_CHANGE, MoneyUtils.toBigInteger(forecast.getPriceChange()))
                .set(FORECAST.PRICE_CHANGE_REL, MoneyUtils.toBigInteger(forecast.getPriceChangeRel()))
                .where(FORECAST.UID.eq(UUID.fromString(forecast.getUid())))
                .execute();
    }

    public Optional<ForecastRecord> update(UUID uuid, UpdateForecastRequest forecast) {
        if (forecast == null) {
            log.warn("Попытка обновить пустой прогноз, не обновляем");
            return Optional.empty();
        }
        return dslContext.update(FORECAST)
                .set(FORECAST.TICKER, forecast.ticker())
                .set(FORECAST.RECOMENDATION, forecast.recommendation().toString())
                .set(FORECAST.CURRENCY, forecast.currency())
                .set(FORECAST.CURRENT_PRICE, MoneyUtils.toBigInteger(forecast.currentPrice()))
                .set(FORECAST.CONSENSUS, MoneyUtils.toBigInteger(forecast.consensus()))
                .set(FORECAST.MIN_TARGET, MoneyUtils.toBigInteger(forecast.minTarget()))
                .set(FORECAST.MAX_TARGET, MoneyUtils.toBigInteger(forecast.maxTarget()))
                .set(FORECAST.PRICE_CHANGE, MoneyUtils.toBigInteger(forecast.priceChange()))
                .set(FORECAST.PRICE_CHANGE_REL, MoneyUtils.toBigInteger(forecast.priceChangeRel()))
                .where(FORECAST.UID.eq(uuid))
                .returning().fetchOptional();
    }

    public Optional<ForecastRecord> delete(UUID uid) {
        return dslContext.deleteFrom(FORECAST)
                .where(FORECAST.UID.eq(uid))
                .returning().fetchOptional();
    }
}
