package com.epoch.activitysystem.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ErrorUtils {

  static ErrorEntity createError(
    final String message,
    final String code,
    final Integer httpStatusCode
  ) {
    ErrorEntity error = new ErrorEntity();
    error.setMessage(message);
    error.setErrorCode(code);
    error.setStatus(httpStatusCode);
    return error;
  }
}
