package dev.invest.service;


import dev.invest.model.auth.AuthRequest;
import dev.invest.model.auth.AuthResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenStoreService tokenStoreService;

    public AuthResponse authenticate(AuthRequest authRequest, HttpServletResponse response) {
        var authentication = new UsernamePasswordAuthenticationToken(
                authRequest.login(), authRequest.password());

        try {
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new IllegalArgumentException("Invalid email or password.", e);
        } catch (AuthenticationException e) {
            throw new IllegalArgumentException("Authentication error");
        }

        String accessToken = jwtService.generateAccessToken(authRequest.login());
        String refreshToken = jwtService.generateRefreshToken(authRequest.login());

        // Добавляем refresh token в cookie
        addRefreshTokenCookie(response, refreshToken);

        // Сохраняем токен в хранилище
        tokenStoreService.storeRefreshToken(refreshToken, jwtService.extractExpiration(refreshToken));

        return new AuthResponse(accessToken);
    }

    public AuthResponse refreshToken(HttpServletRequest request, HttpServletResponse response) {
        // 1. Проверка наличия кук
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            throw new IllegalArgumentException("No cookies found");
        }

        // 2. Получение refresh token из cookie
        String oldRefreshToken = Arrays.stream(cookies)
                .filter(c -> "refreshToken".equals(c.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new IllegalArgumentException("Refresh token not found"));

        // 3. Проверка типа токена
        if (!jwtService.isRefreshToken(oldRefreshToken)) {
            throw new IllegalArgumentException("Invalid token type - refresh token expected");
        }

        // 4. Извлечение имени пользователя
        String username;
        try {
            username = jwtService.extractUsername(oldRefreshToken);
        } catch (JwtException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid token structure", e);
        }

        // 5. Полная проверка валидности токена
        if (!jwtService.isTokenValid(oldRefreshToken, username) ||
                !tokenStoreService.isRefreshTokenValid(oldRefreshToken)) {
            throw new IllegalArgumentException("Invalid or expired refresh token");
        }

        // 6. Инвалидация старого токена
        tokenStoreService.invalidateRefreshToken(oldRefreshToken);

        // 7. Генерация новых токенов
        String newAccessToken = jwtService.generateAccessToken(username);
        String newRefreshToken = jwtService.generateRefreshToken(username);

        // 8. Обновление хранилища и кук
        tokenStoreService.storeRefreshToken(newRefreshToken, jwtService.extractExpiration(newRefreshToken));
        addRefreshTokenCookie(response, newRefreshToken);

        // 9. Возврат нового access token
        return new AuthResponse(newAccessToken);
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        // 1. Получаем refresh token из cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Arrays.stream(cookies)
                    .filter(c -> "refreshToken".equals(c.getName()))
                    .findFirst()
                    .ifPresent(cookie -> {
                        // 2. Инвалидируем токен
                        tokenStoreService.invalidateRefreshToken(cookie.getValue());

                        // 3. Очищаем cookie
                        Cookie clearCookie = new Cookie("refreshToken", null);
                        clearCookie.setHttpOnly(true);
                        clearCookie.setSecure(true);
                        clearCookie.setPath("/api/auth/refresh");
                        clearCookie.setMaxAge(0); // Удаляем cookie
                        response.addCookie(clearCookie);
                    });
        }
    }

    private void addRefreshTokenCookie(HttpServletResponse response, String refreshToken) {
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/api/auth/refresh");
        cookie.setMaxAge((int) Duration.ofDays(14).toSeconds());
        response.addCookie(cookie);
    }
}
