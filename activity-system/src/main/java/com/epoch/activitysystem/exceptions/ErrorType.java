package com.epoch.activitysystem.exceptions;

import com.epoch.activitysystem.configuration.constants.enums.ErrorTypes;
import com.epoch.activitysystem.configuration.constants.swagger.SwaggerConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {
  GENERIC_ERROR(
    ErrorTypes.SERVER_ERROR,
    "System is unable to complete the request"
  ),
  GENERIC_ALREADY_EXISTS(ErrorTypes.CLIENT_ERROR, "Already exists"),
  METHOD_ARGUMENT_NOT_VALID(ErrorTypes.CLIENT_ERROR, "Invalid argument."),
  EMPLOYEE_NOT_FOUND(
    ErrorTypes.CLIENT_ERROR,
    SwaggerConstants.EMPLOYEE_NOT_FOUND
  ),
  DATA_INTEGRITY_VIOLATION(
    ErrorTypes.CLIENT_ERROR,
    "One or more of the fields is invalid."
  );

  private String type;
  private String message;
}
