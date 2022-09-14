package com.epoch.activitysystem.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConstraintViolationException extends RuntimeException {

  private static final long serialVersion = 1L;
  private final String errorMessage;
  private final String errorCode;

  public ConstraintViolationException() {
    super(ErrorCode.CONSTRAINT_VIOLATION.getMessage());
    this.errorCode = ErrorCode.CONSTRAINT_VIOLATION.getCode();
    this.errorMessage = ErrorCode.CONSTRAINT_VIOLATION.getMessage();
  }
}
