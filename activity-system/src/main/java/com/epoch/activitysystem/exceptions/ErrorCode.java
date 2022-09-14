package com.epoch.activitysystem.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  GENERIC_ERROR("ACTIVITY-0001", "System is unable to complete the request"),
  GENERIC_ALREADY_EXISTS("ACTIVITY-0002", "Already exists"),
  METHOD_ARGUMENT_NOT_VALID("ACTIVITY-0003", "Invalid argument."),
  EMPLOYEE_NOT_FOUND("ACTIVITY-0004", "Employee not found."),
  DATA_INTEGRITY_VIOLATION(
    "ACTIVITY-0005",
    "One or more of the fields is invalid."
  );

  private String code;
  private String message;
}
