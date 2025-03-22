package dev.invest.handler;

import dev.invest.exception.ExceptionDetails;
import dev.invest.exception.InvestException;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvestException.class)
    public ResponseEntity<ExceptionDetails> handleInvestException(InvestException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ExceptionDetails(
                        ex.getClass().getName(),
                        ex.getMessage(),
                        HttpStatus.BAD_REQUEST
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionDetails> handleInvestException(NoSuchElementException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ExceptionDetails(
                        ex.getClass().getName(),
                        ex.getMessage(),
                        HttpStatus.NOT_FOUND
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDetails> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ExceptionDetails(
                        ex.getClass().getName(),
                        ex.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
