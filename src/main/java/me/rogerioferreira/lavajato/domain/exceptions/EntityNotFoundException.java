package me.rogerioferreira.lavajato.domain.exceptions;

public class EntityNotFoundException extends RuntimeException {
  private static final String formattedMessage = "No \"%s\" was found with id: %s";
  private String fieldName;

  public EntityNotFoundException(String entityName, String fieldName, String id) {
    super(String.format(formattedMessage, entityName, id));
    this.fieldName = fieldName;
  }

  public String getFieldName() {
    return fieldName;
  }
}
