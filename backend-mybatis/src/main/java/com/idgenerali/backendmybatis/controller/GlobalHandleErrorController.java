package com.idgenerali.backendmybatis.controller;

import com.idgenerali.backendmybatis.exception.ResourceConflictException;
import com.idgenerali.backendmybatis.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalHandleErrorController extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handleInternalServerError(Exception e, WebRequest req){
        ResponseError responseError = new ResponseError();
        responseError.setTimestamp(LocalDateTime.now());
        responseError.setError(e.getLocalizedMessage());
        responseError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<ResponseError> handleConflictError(ResourceConflictException e, WebRequest req)
        throws Exception {
        ResponseError responseError = new ResponseError();
        responseError.setTimestamp(LocalDateTime.now());
        responseError.setError(e.getLocalizedMessage());
        responseError.setStatus(HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(responseError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseError> handleNotFoundError(ResourceNotFoundException e, WebRequest req)
        throws Exception {
        ResponseError responseError = new ResponseError();
        responseError.setTimestamp(LocalDateTime.now());
        responseError.setError(e.getLocalizedMessage());
        responseError.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
    }

    public static class ResponseError {

        private LocalDateTime timestamp;
        private int status;
        private String error;

        public ResponseError(){}

        public ResponseError(LocalDateTime timestamp, int status, String error) {
            this.timestamp = timestamp;
            this.status = status;
            this.error = error;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}
