package dev.invest.bootstrap.initializer.impl;

import dev.invest.bootstrap.initializer.Initializer;
import dev.invest.db.repository.FundamentalRepository;
import dev.invest.service.ShareService;
import dev.invest.utils.GenerateUtils;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FundamentalInitializer implements Initializer {
    private final ShareService shareService;
    private final FundamentalRepository fundamentalRepository;

    @Override
    public void initialize() {
        log.info("Инициализируем базу фундаментальных прогнозов");
        List<UUID> assetUuids = shareService.getAll()
                .stream()
                .map(dev.invest.db.jooq.org.jooq.generated.invest.tables.records.ShareRecord::getAssetUid)
                .toList();
        GenerateUtils.buildFundamentalList(assetUuids)
                .forEach(fundamentalRepository::save);
        log.info("База фундаментальных прогнозов инициализирована успешно!");
    }
}
