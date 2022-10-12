package com.epoch.activitysystem.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ErrorUtils {

  public static ErrorEntity createError(
    final String message,
    final String type,
    final Integer httpStatusCode
  ) {
    ErrorEntity error = new ErrorEntity();
    error.setMessage(message);
    error.setType(type);
    error.setStatus(httpStatusCode);
    return error;
  }
}
