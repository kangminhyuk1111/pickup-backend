package core.pickupbackend.global.exception;

import core.pickupbackend.global.common.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MatchException.class)
    public ResponseEntity<ErrorResponse> handleMatchException(MatchException e) {
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(ValidateException.class)
    public ResponseEntity<ErrorResponse> handleValidateException(ValidateException e) {
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(ApplicationException e) {
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        final String message = e.getMessage();
        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        final Integer code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        final ErrorResponse errorResponse = new ErrorResponse(message, code, status);
        return new ResponseEntity<>(errorResponse, status);
    }
}
