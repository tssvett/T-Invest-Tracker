package dev.invest.service;

import dev.invest.db.jooq.org.jooq.generated.invest.tables.records.UsersShareRecord;
import dev.invest.db.repository.PortfolioRepository;
import dev.invest.mapper.PortfolioMapper;
import dev.invest.model.portfolio.PortfolioDto;
import dev.invest.model.share.ShareDto;
import dev.invest.model.user.UserDto;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PortfolioService {
    private final PortfolioRepository repository;
    private final PortfolioMapper mapper;
    private final ShareService shareService;
    private final UserService userService;

    public List<ShareDto> findSharesByUserId(UUID userId) {
        log.info("Получаем акции пользователя {}", userId);
        return repository
                .findSharesByUserId(userId)
                .stream()
                .map(UsersShareRecord::getShareUid)
                .map(shareService::getByUid)
                .toList();
    }

    public List<UserDto> findUsersByShareId(UUID shareId) {
        log.info("Получаем пользователей, которые владеют акцией {}", shareId);
        return repository
                .findUsersByShareUid(shareId)
                .stream()
                .map(UsersShareRecord::getUserId)
                .map(userService::getByUid)
                .toList();
    }

    public PortfolioDto update(PortfolioDto portfolioDto) {
        log.info("Обновляем портфель пользователя {} по акции {}", portfolioDto.userId(), portfolioDto.shareId());
        return mapper.toModel(
                repository.update(mapper.toEntity(portfolioDto))
        );
    }

    public PortfolioDto create(PortfolioDto portfolioDto) {
        log.info("Добавляем портфель пользователя {} по акции {}", portfolioDto.userId(), portfolioDto.shareId());
        return mapper.toModel(
                repository.save(mapper.toEntity(portfolioDto))
        );
    }

    public void delete(PortfolioDto portfolioDto) {
        log.info("Удаляем портфель пользователя {} по акции {}", portfolioDto.userId(), portfolioDto.shareId());
        repository.delete(portfolioDto.userId(), portfolioDto.shareId());
    }
}
