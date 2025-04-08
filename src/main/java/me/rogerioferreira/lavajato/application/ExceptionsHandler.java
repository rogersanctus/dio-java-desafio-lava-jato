package me.rogerioferreira.lavajato.application;

import java.util.Arrays;
import java.util.HashMap;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import jakarta.validation.ConstraintViolationException;
import me.rogerioferreira.lavajato.domain.exceptions.DuplicatedValueException;
import me.rogerioferreira.lavajato.domain.exceptions.EntityNotFoundException;
import me.rogerioferreira.lavajato.domain.exceptions.RelatedEntityNotFoundException;

@RestControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {
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

  @ExceptionHandler(DuplicatedValueException.class)
  public ResponseEntity<?> handleDuplicatedValueException(DuplicatedValueException ex) {
    var error = new HashMap<String, Object>();
    var errors = new HashMap<String, String>();

    error.put("errors", errors);
    errors.put(ex.getField(), ex.getMessage());

    return ResponseEntity.badRequest().body(error);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
    var error = new HashMap<String, Object>();
    var errorMessage = new HashMap<String, String>();

    error.put("error", errorMessage);
    errorMessage.put("message", ex.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  @ExceptionHandler(RelatedEntityNotFoundException.class)
  public ResponseEntity<Object> handleEntityNotFoundException(RelatedEntityNotFoundException ex) {
    var error = new HashMap<String, Object>();
    var errors = new HashMap<String, String>();

    error.put("errors", errors);
    errors.put(ex.getFieldName(), ex.getMessage());

    return ResponseEntity.badRequest().body(error);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
      HttpStatusCode status, WebRequest request) {
    var error = new HashMap<String, Object>();
    var errors = new HashMap<String, String>();

    if (ex.getCause() instanceof InvalidFormatException invalidFormatException) {
      error.put("errors", errors);
      var field = invalidFormatException.getPath().isEmpty() ? "unknown"
          : invalidFormatException.getPath().get(0).getFieldName();

      field = field == null ? "unknown" : field;

      String invalidValue = invalidFormatException.getValue().toString();
      var fieldError = "Invalid value: " + invalidValue;

      if (invalidFormatException.getTargetType().isEnum()) {
        var enumValues = Arrays.stream(invalidFormatException.getTargetType().getEnumConstants())
            .map(enumConstant -> enumConstant.toString())
            .toList()
            .toString();

        fieldError += ". Valid values: " + enumValues.toString();
      }

      errors.put(field, fieldError);

      return ResponseEntity.badRequest().body(error);
    }

    return super.handleHttpMessageNotReadable(ex, headers, status, request);
  }
}
