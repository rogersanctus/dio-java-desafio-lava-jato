package me.rogerioferreira.lavajato.domain.enums;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import me.rogerioferreira.lavajato.domain.exceptions.InvalidLabelForEnumException;

public enum WashingServiceStatus {
  WAITING("waiting", List.of("in_progress", "canceled")),
  IN_PROGRESS("in_progress", List.of("finished", "canceled")),
  FINISHED("finished", Collections.emptyList()),
  CANCELED("canceled", Collections.emptyList());

  public final String label;
  public final List<String> nextStatuses;

  WashingServiceStatus(String label, List<String> nextStatuses) {
    this.label = label;
    this.nextStatuses = nextStatuses;
  }

  @JsonValue
  public String getLabel() {
    return this.label;
  }

  @JsonCreator
  public static WashingServiceStatus fromLabel(String label) throws InvalidLabelForEnumException {
    return List.of(WashingServiceStatus.values())
        .stream()
        .filter(status -> status.label.equals(label))
        .findFirst()
        .orElseThrow(() -> new InvalidLabelForEnumException(WashingServiceStatus.class, label,
            WashingServiceStatus.getLabels()));
  }

  public static List<String> getLabels() {
    return List.of(WashingServiceStatus.values())
        .stream()
        .map(WashingServiceStatus::getLabel)
        .toList();
  }

  @Override
  public String toString() {
    return this.label;
  }
}
