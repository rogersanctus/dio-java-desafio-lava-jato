package me.rogerioferreira.lavajato.domain.exceptions;

public class EntityNotFoundException extends RuntimeException {
  private static final String formattedMessage = "No \"%s\" was found with id: %s";

  public EntityNotFoundException(String entityName, String id) {
    super(String.format(formattedMessage, entityName, id));
  }
}
