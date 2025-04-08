package me.rogerioferreira.lavajato.domain.exceptions;

import java.util.List;

public class InvalidLabelForEnumException extends RuntimeException {
  private static final String formattedMessage = "No %s enum value found with label: %s. Possible labels are: %s";

  public InvalidLabelForEnumException(Class<?> enumType, String label, List<String> possibleLabels) {
    super(String.format(formattedMessage, enumType.getSimpleName(), label, possibleLabels));
  }
}
