package dev.invest.bootstrap;

import dev.invest.service.ShareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = "invest.share.initializer.enabled", havingValue = "true")
public class ShareInitializer {
    private final ShareService shareService;

    @EventListener(ApplicationStartedEvent.class)
    public void initialize() {
        shareService.initialize();
    }
}
