package me.rogerioferreira.lavajato.application;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex) {
    var error = new HashMap<String, Object>();
    var errors = new HashMap<String, String>();

    error.put("errors", errors);

    for (var violation : ex.getConstraintViolations()) {
      errors.put(violation.getPropertyPath().toString(), violation.getMessage());
    }

    return ResponseEntity.badRequest().body(error);
  }
}
