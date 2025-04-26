package dev.invest.exception;

public class JwtExpiredException extends RuntimeException {
    public JwtExpiredException(String invalidOrExpiredRefreshToken) {
        super(invalidOrExpiredRefreshToken);
    }
}
