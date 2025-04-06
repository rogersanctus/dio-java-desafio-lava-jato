package me.rogerioferreira.lavajato.domain.exceptions;

public class DuplicatedValueException extends RuntimeException {
  private String field;
  private Object value;

  public DuplicatedValueException(String field, Object value) {
    super(String.format("has duplicated value: %s", value.toString()));

    this.field = field;
    this.value = value;
  }

  public String getField() {
    return field;
  }

  public Object getValue() {
    return value;
  }
}
