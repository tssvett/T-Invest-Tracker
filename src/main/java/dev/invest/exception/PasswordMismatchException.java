package dev.invest.exception;

public class PasswordMismatchException extends RuntimeException {
    public PasswordMismatchException(String s) {
        super(s);
    }
}
