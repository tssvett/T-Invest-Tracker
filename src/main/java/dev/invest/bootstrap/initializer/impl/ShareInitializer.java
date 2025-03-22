package dev.invest.bootstrap.initializer.impl;

import dev.invest.bootstrap.initializer.Initializer;
import dev.invest.db.repository.ShareRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.tinkoff.piapi.contract.v1.Share;
import ru.tinkoff.piapi.core.InstrumentsService;

@Slf4j
@Component
@RequiredArgsConstructor
public class ShareInitializer implements Initializer {
    private final ShareRepository shareRepository;
    private final InstrumentsService instrumentsService;

    @Override
    public void initialize() {
        log.info("Инициализируем базу акций");
        List<Share> allSharesSync = instrumentsService.getAllSharesSync();
        log.info("Найдено {} акций", allSharesSync.size());
        allSharesSync.forEach(shareRepository::save);
        log.info("База акций инициализирована успешно!");
    }
}
