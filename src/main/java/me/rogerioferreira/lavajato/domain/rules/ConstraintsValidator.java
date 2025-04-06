package me.rogerioferreira.lavajato.domain.rules;

import jakarta.validation.ConstraintViolationException;

public interface ConstraintsValidator {
  void validate(Object object, Class<?>... groups) throws ConstraintViolationException;
}
