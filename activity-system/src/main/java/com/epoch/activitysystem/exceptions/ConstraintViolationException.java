package com.epoch.activitysystem.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConstraintViolationException extends RuntimeException {

  private static final long serialVersion = 1L;
  private final String errorMessage;
  private final String errorType;

  public ConstraintViolationException() {
    super(ErrorType.DATA_INTEGRITY_VIOLATION.getMessage());
    this.errorType = ErrorType.DATA_INTEGRITY_VIOLATION.getType();
    this.errorMessage = ErrorType.DATA_INTEGRITY_VIOLATION.getMessage();
  }
}
