package core.pickupbackend.global.exception;

import core.pickupbackend.global.common.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DatabaseExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(DatabaseExceptionHandler.class);

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        final String message = "DataIntegrityViolationException: " + e.getMessage();
        final HttpStatus status = HttpStatus.CONFLICT;
        final Integer code = status.value();
        final ErrorResponse errorResponse = new ErrorResponse(message, code, status);
        logger.error(errorResponse.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(QueryTimeoutException.class)
    public ResponseEntity<ErrorResponse> handleQueryTimeout(QueryTimeoutException e) {
        final String message = "QueryTimeoutException: " + e.getMessage();
        final HttpStatus status = HttpStatus.REQUEST_TIMEOUT;
        final Integer code = status.value();
        final ErrorResponse errorResponse = new ErrorResponse(message, code, status);
        logger.error(errorResponse.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(DataAccessResourceFailureException.class)
    public ResponseEntity<ErrorResponse> handleDataAccessResourceFailure(DataAccessResourceFailureException e) {
        final String message = "QueryTimeoutException: " + e.getMessage();
        final HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
        final Integer code = status.value();
        final ErrorResponse errorResponse = new ErrorResponse(message, code, status);
        logger.error(errorResponse.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(CannotAcquireLockException.class)
    public ResponseEntity<ErrorResponse> handleCannotAcquireLock(CannotAcquireLockException e) {
        final String message = "QueryTimeoutException: " + e.getMessage();
        final HttpStatus status = HttpStatus.CONFLICT;
        final Integer code = status.value();
        final ErrorResponse errorResponse = new ErrorResponse(message, code, status);
        logger.error(errorResponse.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponse> handleDataAccess(DataAccessException e) {
        final String message = "QueryTimeoutException: " + e.getMessage();
        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        final Integer code = status.value();
        final ErrorResponse errorResponse = new ErrorResponse(message, code, status);
        logger.error(errorResponse.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }
}
