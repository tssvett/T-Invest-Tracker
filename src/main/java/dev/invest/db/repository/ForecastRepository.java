package dev.invest.db.repository;

import static dev.invest.db.jooq.org.jooq.generated.invest.tables.Forecast.FORECAST;
import dev.invest.db.jooq.org.jooq.generated.invest.tables.records.ForecastRecord;
import dev.invest.utils.MoneyUtils;
import java.util.List;
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

    public List<ForecastRecord> getAll() {
        return dslContext.selectFrom(FORECAST)
                .fetch();
    }
}
