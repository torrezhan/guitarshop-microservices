package com.example.customer.handler;

import com.example.customer.exception.CustomerNotFoundException;
import com.example.customer.exception.EmailAlreadyExistsException;
import com.example.customer.exception.UnauthorizedAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CustomerNotFoundException.class)
  public ResponseEntity<String> handle(CustomerNotFoundException exp) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(exp.getMessage());
  }

  @ExceptionHandler(EmailAlreadyExistsException.class)
  public ResponseEntity<String> handle(EmailAlreadyExistsException exp) {
    return ResponseEntity
        .status(BAD_REQUEST)
        .body(exp.getMessage());
  }

  @ExceptionHandler(UnauthorizedAccessException.class)
  public ResponseEntity<String> handle(UnauthorizedAccessException exp) {
    return ResponseEntity
        .status(FORBIDDEN)
        .body(exp.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
    var errors = new HashMap<String, String>();
    exp.getBindingResult().getAllErrors()
            .forEach(error -> {
              var fieldName = ((FieldError) error).getField();
              var errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });

    return ResponseEntity
            .status(BAD_REQUEST)
            .body(new ErrorResponse(errors));
  }
}
