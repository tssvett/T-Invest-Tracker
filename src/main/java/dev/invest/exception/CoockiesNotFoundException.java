package dev.invest.exception;

public class CoockiesNotFoundException extends RuntimeException {
    public CoockiesNotFoundException(String noCookiesFound) {
        super(noCookiesFound);
    }
}
