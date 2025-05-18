package dev.invest.service;

import dev.invest.db.jooq.org.jooq.generated.invest.tables.records.UsersShareRecord;
import dev.invest.db.repository.OwnedShareRepository;
import dev.invest.mapper.PortfolioMapper;
import dev.invest.model.portfolio.OwnedShareCreateDto;
import dev.invest.model.portfolio.OwnedShareDto;
import dev.invest.model.share.ShareDto;
import dev.invest.model.user.UserDto;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OwnedShareService {
    private final OwnedShareRepository repository;
    private final PortfolioMapper mapper;
    private final ShareService shareService;
    private final UserService userService;

    public List<OwnedShareDto> findSharesByUserId(UUID userId) {
        log.info("Получаем акции пользователя {}", userId);
        return repository
                .findSharesByUserId(userId)
                .stream()
                .map(mapper::toModel)
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

    public OwnedShareDto update(OwnedShareDto ownedShareDto) {
        log.info("Обновляем портфель пользователя {} по акции {}", ownedShareDto.userId(), ownedShareDto.shareId());
        return mapper.toModel(
                repository.update(mapper.toEntity(ownedShareDto))
        );
    }

    public OwnedShareDto create(OwnedShareCreateDto ownedShareDto) {
        log.info("Добавляем портфель пользователя {} по акции {}", ownedShareDto.userId(), ownedShareDto.shareId());
        return mapper.toModel(
                repository.save(mapper.toEntity(
                        ownedShareDto,
                        shareService.getByUid(ownedShareDto.shareId()).nominal(),
                        new Random().nextInt(1, 2000000))
                ));
    }

    public void delete(OwnedShareDto ownedShareDto) {
        log.info("Удаляем портфель пользователя {} по акции {}", ownedShareDto.userId(), ownedShareDto.shareId());
        repository.delete(ownedShareDto.userId(), ownedShareDto.shareId());
    }
}
