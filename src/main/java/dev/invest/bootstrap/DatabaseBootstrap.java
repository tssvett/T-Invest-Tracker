package dev.invest.bootstrap;

import dev.invest.service.Initializer;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = "invest.initializer.enabled", havingValue = "true")
public class DatabaseBootstrap {
    private final List<Initializer> initializerList;

    @EventListener(ApplicationStartedEvent.class)
    public void initialize() {
        initializerList.forEach(Initializer::initialize);
    }
}
