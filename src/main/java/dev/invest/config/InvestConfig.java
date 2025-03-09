package dev.invest.config;

import dev.invest.properties.InvestProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.piapi.core.InstrumentsService;
import ru.tinkoff.piapi.core.InvestApi;

@Configuration
@RequiredArgsConstructor
public class InvestConfig {
    private final InvestProperties investProperties;

    @Bean
    public InvestApi investApi() {
        return InvestApi.create(investProperties.token());
    }

    @Bean
    public InstrumentsService instrumentsService() {
        return investApi().getInstrumentsService();
    }
}
