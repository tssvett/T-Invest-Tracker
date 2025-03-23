package dev.invest.bootstrap.initializer.impl;

import dev.invest.bootstrap.initializer.Initializer;
import dev.invest.db.repository.ForecastRepository;
import dev.invest.service.ShareService;
import dev.invest.utils.GenerateUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ForecastInitializer implements Initializer {
    private final ForecastRepository forecastRepository;
    private final ShareService shareService;

    @Override
    public void initialize() {
        log.info("Инициализируем базу прогнозов");
        List<String> tickers = shareService.getAll()
                .stream()
                .map(dev.invest.db.jooq.org.jooq.generated.invest.tables.records.ShareRecord::getTicker)
                .toList();
        GenerateUtils.buildConsensusList(tickers)
                .forEach(forecastRepository::save);
        log.info("База прогнозов инициализирована успешно!");
    }
}
