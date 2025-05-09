package dev.invest.exception;

public class InvalidJwtTypeException extends RuntimeException {
    public InvalidJwtTypeException(String s) {
        super(s);
    }
}
