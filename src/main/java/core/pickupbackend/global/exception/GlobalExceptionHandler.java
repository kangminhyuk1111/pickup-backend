package core.pickupbackend.global.exception;

import com.google.firebase.FirebaseException;
import core.pickupbackend.global.common.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ApplicationMatchException.class)
    public ResponseEntity<ErrorResponse> handleMatchException(ApplicationMatchException e) {
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode);
        logger.error(errorCode.getMessage());
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
    }

    @ExceptionHandler(ValidateException.class)
    public ResponseEntity<ErrorResponse> handleValidateException(ValidateException e) {
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode);
        logger.error(errorCode.getMessage());
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(ApplicationException e) {
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode);
        logger.error(errorCode.getMessage());
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
    }

    @ExceptionHandler(FirebaseException.class)
    public ResponseEntity<ErrorResponse> handleFirebaseException(FirebaseException e) {
        final String message = e.getMessage();
        final int code = e.getHttpResponse().getStatusCode();
        final HttpStatus status = HttpStatus.valueOf(code);
        final ErrorResponse errorResponse = new ErrorResponse(message, code, status);
        logger.error(errorResponse.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        final String message = e.getMessage();
        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        final Integer code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        final ErrorResponse errorResponse = new ErrorResponse(message, code, status);
        logger.error(errorResponse.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }
}
