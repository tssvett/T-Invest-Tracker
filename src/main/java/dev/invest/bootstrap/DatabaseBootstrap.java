package dev.invest.bootstrap;

import dev.invest.bootstrap.initializer.Initializer;
import dev.invest.bootstrap.initializer.impl.ShareInitializer;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseBootstrap {
    private final List<Initializer> initializerList;

    public void initialize() {
        // Сначала выполняем инициализацию акций
        initializerList.stream()
                .filter(ShareInitializer.class::isInstance)
                .findFirst()
                .ifPresent(Initializer::initialize);

        // Затем выполняем инициализацию для всего остального
        initializerList.stream()
                .filter(initializer -> !(initializer instanceof ShareInitializer))
                .forEach(Initializer::initialize);
    }
}