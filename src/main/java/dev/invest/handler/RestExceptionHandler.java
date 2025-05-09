package dev.invest.handler;

import dev.invest.exception.AlreadyExistsException;
import dev.invest.exception.CoockiesNotFoundException;
import dev.invest.exception.ExceptionDetails;
import dev.invest.exception.InvalidJwtTypeException;
import dev.invest.exception.InvalidTokenStructureException;
import dev.invest.exception.InvestException;
import dev.invest.exception.JwtExpiredException;
import dev.invest.exception.PasswordMismatchException;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.jooq.exception.IntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

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

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionDetails> handleInvestException(NullPointerException ex) {
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
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

    @ExceptionHandler(IntegrityConstraintViolationException.class)
    public ResponseEntity<ExceptionDetails> handleIntegrityConstraintViolationException(IntegrityConstraintViolationException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ExceptionDetails(
                        ex.getClass().getName(),
                        ex.getMessage(),
                        HttpStatus.BAD_REQUEST
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionDetails> handleIllegalArgumentException(IllegalArgumentException ex) {
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

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ExceptionDetails> handleIllegalStateException(IllegalStateException ex) {
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

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ExceptionDetails> handleAlreadyExistsException(AlreadyExistsException ex) {
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

    @ExceptionHandler(CoockiesNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleCoockiesNotFoundException(CoockiesNotFoundException ex) {
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

    @ExceptionHandler(InvalidJwtTypeException.class)
    public ResponseEntity<ExceptionDetails> handleInvalidJwtTypeException(InvalidJwtTypeException ex) {
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

    @ExceptionHandler(InvalidTokenStructureException.class)
    public ResponseEntity<ExceptionDetails> handleInvalidTokenStructureException(InvalidTokenStructureException ex) {
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

    @ExceptionHandler(JwtExpiredException.class)
    public ResponseEntity<ExceptionDetails> handleJwtExpiredException(JwtExpiredException ex) {
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

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ExceptionDetails> handlePasswordMismatchException(PasswordMismatchException ex) {
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

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ExceptionDetails> handleAuthorizationDeniedException(AuthorizationDeniedException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ExceptionDetails(
                        ex.getClass().getName(),
                        ex.getMessage(),
                        HttpStatus.FORBIDDEN
                ),
                HttpStatus.FORBIDDEN
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
