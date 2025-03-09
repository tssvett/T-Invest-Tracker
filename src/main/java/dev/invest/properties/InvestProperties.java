package dev.invest.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "invest")
public record InvestProperties(
        String token
) {
}
