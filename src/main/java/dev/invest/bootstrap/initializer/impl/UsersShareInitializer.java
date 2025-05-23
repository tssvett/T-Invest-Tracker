package dev.invest.bootstrap.initializer.impl;

import dev.invest.bootstrap.initializer.Initializer;
import dev.invest.db.jooq.org.jooq.generated.invest.tables.records.ShareRecord;
import dev.invest.db.repository.UsersShareRepository;
import dev.invest.model.user.UserDto;
import dev.invest.service.ShareService;
import dev.invest.service.UserService;
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
public class UsersShareInitializer implements Initializer {
    private final UsersShareRepository usersShareRepository;
    private final UserService userService;
    private final ShareService shareService;

    @Override
    public void initialize() {
        log.info("Инициализируем базу акций пользователя");
        List<UUID> userIds = userService.findAll()
                .stream()
                .map(UserDto::id)
                .toList();

        List<UUID> shareIds = shareService.getAll()
                .stream()
                .map(ShareRecord::getUid)
                .toList();
        GenerateUtils.buildUserShareList(userIds, shareIds)
                .forEach(usersShareRepository::save);
        log.info("База акций пользователя инициализирована успешно!");
    }
}
