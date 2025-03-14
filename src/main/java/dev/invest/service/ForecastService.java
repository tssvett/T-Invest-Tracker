package dev.invest.service;

import dev.invest.db.jooq.org.jooq.generated.invest.tables.records.ShareRecord;
import dev.invest.db.repository.ForecastRepository;
import dev.invest.utils.GenerateUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ForecastService implements Initializer {
    private final ForecastRepository forecastRepository;
    private final ShareService shareService;

    @Override
    public void initialize() {
        log.info("Инициализируем базу прогнозов");
        List<String> tickers = shareService.getAll()
                .stream()
                .map(ShareRecord::getTicker)
                .toList();
        GenerateUtils.buildConsensusList(tickers)
                .forEach(forecastRepository::save);
        log.info("База прогнозов инициализирована успешно!");
    }
}
