package edu.miu.eaproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //it catches exception from controller
public class BadgeSystemExceptionHandler {
    @ExceptionHandler(BadgeNotAcceptedException.class)
    public ResponseEntity<?> handleException(BadgeNotAcceptedException exception){
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode(), exception.getErrorMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleException(NotFoundException exception){
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode(), exception.getErrorMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    public class ErrorResponse{
        private String errorCode;
        private String errorResponse;

        public ErrorResponse(String errorCode, String errorResponse) {
            this.errorCode = errorCode;
            this.errorResponse = errorResponse;
        }

        public String getErrorResponse() {
            return errorResponse;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorResponse(String errorResponse) {
            this.errorResponse = errorResponse;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }
    }
}
