package dev.invest.exception;

public class InvalidTokenStructureException extends RuntimeException {
    public InvalidTokenStructureException(String invalidTokenStructure, RuntimeException e) {
        super(invalidTokenStructure, e);
    }
}
