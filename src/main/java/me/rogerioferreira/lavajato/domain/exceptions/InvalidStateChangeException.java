package me.rogerioferreira.lavajato.domain.exceptions;

import java.util.List;

public class InvalidStateChangeException extends RuntimeException {
  private static final String formattedMessage = "Invalid state change from \"%s\" to \"%s\". %s";
  private String previousState;
  private String nextState;
  private List<String> possibleNextStates;

  public InvalidStateChangeException(String previousState, String nextState, List<String> possibleNextStates) {
    // super(String.format(formattedMessage, previousState, nextState,
    // possibleNextStates));
    super();
    this.previousState = previousState;
    this.nextState = nextState;
    this.possibleNextStates = possibleNextStates;
  }

  @Override
  public String getMessage() {
    var mappedPossibleNextStates = this.possibleNextStates
        .stream()
        .map(state -> String.format("\"%s\"", state))
        .toList();

    var possibleStatesMessage = this.possibleNextStates.isEmpty()
        ? String.format("The state \"%s\" is the final state", this.previousState)
        : String.format("Possible next states: %s", mappedPossibleNextStates);

    return String.format(formattedMessage, this.previousState, this.nextState, possibleStatesMessage);
  }
}
