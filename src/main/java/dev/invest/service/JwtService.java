package dev.invest.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtService {
    private final JwtEncoder encoder;
    private final JwtDecoder decoder;

    /**
     * Генерирует новый JWT для указанного пользователя.
     *
     * @param username имя пользователя, для которого создается токен
     * @return строковое представление сгенерированного JWT
     * @throws IllegalArgumentException если {@code username} пустой или null
     */
    public String generateAccessToken(String username) {

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty or null.");
        }

        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("invest.ru")
                .issuedAt(now)
                .expiresAt(now.plus(10, ChronoUnit.MINUTES))
                .subject(username)
                .build();

        String token = this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        log.debug("Token created for user: {}", username);

        return token;
    }

    public String generateRefreshToken(String username) {

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty or null.");
        }

        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("invest.ru")
                .issuedAt(now)
                .expiresAt(now.plus(14, ChronoUnit.DAYS))
                .subject(username)
                .claim("token_type", "refresh")
                .build();

        String token = this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        log.debug("Token created for user: {}", username);

        return token;
    }

    /**
     * Проверяет, валиден ли токен для данного пользователя.
     *
     * @param token          JWT-токен
     * @param actualUsername имя пользователя
     * @return true, если токен валиден и принадлежит пользователю
     */
    public boolean isTokenValid(String token, String actualUsername) {
        try {
            Jwt jwt = decodeJwt(token);
            validateTokenTimes(jwt);

            String jwtUsername = jwt.getSubject();
            boolean isUsernameValid = jwtUsername.equals(actualUsername);
            boolean isTokenExpired = jwt.getExpiresAt().isBefore(Instant.now());

            return isUsernameValid && !isTokenExpired;
        } catch (JwtException | IllegalArgumentException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Извлекает имя пользователя из заданного JWT
     *
     * @param token JWT, из которого необходимо извлечь имя пользователя
     * @return имя пользователя, указанное в токене
     * @throws IllegalArgumentException если токен не является действительным
     */
    public String extractUsername(String token) {
        Jwt jwt = decodeJwt(token);
        validateTokenTimes(jwt);

        String username = jwt.getSubject();

        log.info("Extracted username from accessToken: {}", username);

        return username;
    }

    public boolean isRefreshToken(String token) {
        try {
            Jwt jwt = decoder.decode(token);
            return "refresh".equals(jwt.getClaim("token_type"));
        } catch (JwtException e) {
            return false;
        }
    }

    public Instant extractExpiration(String token) {
        Jwt jwt = decodeJwt(token);
        return jwt.getExpiresAt();
    }

    private Jwt decodeJwt(String token) {
        try {
            return this.decoder.decode(token);
        } catch (JwtException e) {
            log.error("Invalid JWT accessToken: {}", e.getMessage());

            throw new IllegalArgumentException("Invalid JWT accessToken", e);
        }
    }

    private void validateTokenTimes(Jwt jwt) {
        Instant now = Instant.now();

        validateTime(jwt.getExpiresAt(), now, "JWT accessToken has expired", false);
        validateTime(jwt.getNotBefore(), now, "JWT accessToken is not yet valid", true);
    }

    private void validateTime(Instant tokenTime, Instant now, String errorMessage, boolean isNotBefore) {
        if (tokenTime != null) {

            boolean invalid = isNotBefore ? tokenTime.isAfter(now) : tokenTime.isBefore(now);

            if (invalid) {
                log.error(errorMessage);
                throw new IllegalArgumentException(errorMessage);
            }
        }
    }
}
