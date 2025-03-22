package dev.invest.service;

import dev.invest.db.jooq.org.jooq.generated.invest.tables.records.ShareRecord;
import dev.invest.db.repository.ShareRepository;
import dev.invest.model.page.Pagination;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShareService {
    private final ShareRepository shareRepository;

    public List<ShareRecord> getAllPagination(Pagination pagination) {
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
}
