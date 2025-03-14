package dev.invest.service;


import dev.invest.db.repository.FundamentalRepository;
import dev.invest.utils.GenerateUtils;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FundamentalService implements Initializer {
    private final FundamentalRepository fundamentalRepository;
    private final ShareService shareService;

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
