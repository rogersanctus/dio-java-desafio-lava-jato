package me.rogerioferreira.lavajato.application.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintViolationException;
import me.rogerioferreira.lavajato.domain.rules.ConstraintsValidator;

@Component
public class ApplicationConstraintsValidator implements ConstraintsValidator {
  @Autowired
  private jakarta.validation.Validator validator;

  public void validate(Object object, Class<?>... groups) throws ConstraintViolationException {
    var violations = this.validator.validate(object, groups);

    if (violations.size() > 0) {
      throw new ConstraintViolationException(violations);
    }
  }
}
