package dev.invest.exception;

import org.springframework.http.HttpStatus;

public record ExceptionDetails(
        String exceptionName,
        String exceptionDetails,
        HttpStatus httpStatus
) {
}
