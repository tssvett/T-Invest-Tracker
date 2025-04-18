package dev.invest.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class TokenStoreService {
    private final ConcurrentHashMap<String, Instant> validTokens = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Instant> blacklist = new ConcurrentHashMap<>();

    public void storeRefreshToken(String token, Instant expiresAt) {
        validTokens.put(token, expiresAt);
    }

    public boolean isRefreshTokenValid(String token) {
        // Проверяем что токен не в блэклисте и все еще валиден
        Instant expiry = validTokens.get(token);
        return expiry != null
                && expiry.isAfter(Instant.now())
                && !blacklist.containsKey(token);
    }

    public void invalidateRefreshToken(String token) {
        // Удаляем из активных токенов
        validTokens.remove(token);

        // Добавляем в блэклист на 24 часа
        blacklist.put(token, Instant.now().plus(24, ChronoUnit.HOURS));
    }
}
