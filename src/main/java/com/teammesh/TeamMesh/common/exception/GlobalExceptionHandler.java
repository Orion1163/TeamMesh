package com.teammesh.TeamMesh.common.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleEmailAlreadyExists(EmailAlreadyExistsException exception){
        ApiErrorResponse response = new ApiErrorResponse(false, HttpStatus.CONFLICT.value(),
        exception.getMessage(),
        Instant.now(),
        null);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationErrors(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        ApiErrorResponse response = new ApiErrorResponse(false, HttpStatus.BAD_REQUEST.value(), "Validation Failed", Instant.now(), errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }



    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgument(IllegalArgumentException exception){
        ApiErrorResponse response = new ApiErrorResponse(false, HttpStatus.BAD_REQUEST.value(), exception.getMessage(), Instant.now(), null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneralException(Exception exception){
        ApiErrorResponse response = new ApiErrorResponse(false,HttpStatus.INTERNAL_SERVER_ERROR.value(), "An Unexpected Error Occurred", Instant.now(), null);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceNotFound(ResourceNotFoundException exception){
        ApiErrorResponse response = new ApiErrorResponse(false, HttpStatus.NOT_FOUND.value(), exception.getMessage(), Instant.now(), null);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MemberAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleMemberAlreadyExists(MemberAlreadyExistsException exception){
        ApiErrorResponse response = new ApiErrorResponse(false, HttpStatus.CONFLICT.value(), exception.getMessage(), Instant.now(), null);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

}
