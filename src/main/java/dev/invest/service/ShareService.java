package dev.invest.service;

import dev.invest.db.jooq.org.jooq.generated.invest.tables.records.ShareRecord;
import dev.invest.db.repository.ShareRepository;
import dev.invest.model.page.Pagination;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tinkoff.piapi.contract.v1.Share;
import ru.tinkoff.piapi.core.InstrumentsService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShareService implements Initializer {
    private final InstrumentsService instrumentsService;
    private final ShareRepository shareRepository;

    public List<dev.invest.db.jooq.org.jooq.generated.invest.tables.records.ShareRecord> getAllPagination(Pagination pagination) {
        if (pagination == null) {
            pagination = new Pagination(0, 100);
        }
        var shareList = shareRepository.getAllOffsetLimit(pagination.page(), pagination.size());
        log.info("Получено {} акций из базы", shareList.size());

        return shareList;
    }

    public List<ShareRecord> getAll() {
        var shareList = shareRepository.getAll();
        log.info("Получено {} акций из базы", shareList.size());

        return shareList;
    }

    @Override
    public void initialize() {
        log.info("Инициализируем базу акций");
        List<Share> allSharesSync = instrumentsService.getAllSharesSync();
        log.info("Найдено {} акций", allSharesSync.size());
        allSharesSync.forEach(shareRepository::save);
        log.info("База акций инициализирована успешно!");
    }
}
