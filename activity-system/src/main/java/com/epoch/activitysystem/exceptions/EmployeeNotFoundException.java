package com.epoch.activitysystem.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeNotFoundException extends RuntimeException {

  private final String errorMessage;
  private final String errorCode;
  private static final long serialVersion = 1L;

  public EmployeeNotFoundException() {
    super(ErrorCode.EMPLOYEE_NOT_FOUND.getMessage());
    this.errorCode = ErrorCode.EMPLOYEE_NOT_FOUND.getCode();
    this.errorMessage = ErrorCode.EMPLOYEE_NOT_FOUND.getMessage();
  }
}
