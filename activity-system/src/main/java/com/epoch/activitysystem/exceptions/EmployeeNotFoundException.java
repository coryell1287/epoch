package com.epoch.activitysystem.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeNotFoundException extends RuntimeException {

  private final String errorMessage;
  private final String errorType;
  private static final long serialVersion = 1L;

  public EmployeeNotFoundException() {
    super(ErrorType.EMPLOYEE_NOT_FOUND.getMessage());
    this.errorType = ErrorType.EMPLOYEE_NOT_FOUND.getType();
    this.errorMessage = ErrorType.EMPLOYEE_NOT_FOUND.getMessage();
  }
}
