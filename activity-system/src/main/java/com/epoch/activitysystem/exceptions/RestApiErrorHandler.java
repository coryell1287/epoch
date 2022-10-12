package com.epoch.activitysystem.exceptions;

import java.time.Instant;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import lombok.extern.slf4j.Slf4j;

// ConstraintViolationException
// IllegalStateException
// NoSuchElementException
@Slf4j
@ControllerAdvice
public class RestApiErrorHandler {

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<ErrorEntity> handleGenericExceptionHandler(
    HttpServletRequest request,
    Exception ex
  ) {
    ErrorEntity error = ErrorUtils
      .createError(
        ErrorType.GENERIC_ERROR.getMessage(),
        ErrorType.GENERIC_ERROR.getType(),
        HttpStatus.INTERNAL_SERVER_ERROR.value()
      )
      .setUrl(request.getRequestURL().toString())
      .setRequestMethod(request.getMethod())
      .setTimestamp(Instant.now());

    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorEntity> handleMethodArgumentNotValidException(
    HttpServletRequest request,
    MethodArgumentNotValidException ex
  ) {
    ErrorEntity error = ErrorUtils
      .createError(
        ErrorType.METHOD_ARGUMENT_NOT_VALID.getMessage(),
        ErrorType.METHOD_ARGUMENT_NOT_VALID.getMessage(),
        HttpStatus.BAD_REQUEST.value()
      )
      .setUrl(request.getRequestURL().toString())
      .setRequestMethod(request.getMethod())
      .setTimestamp(Instant.now());

    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(EmployeeNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ErrorEntity> handleEmployeeNotFoundException(
    HttpServletRequest request,
    EmployeeNotFoundException ex
  ) {
    ErrorEntity error = ErrorUtils
      .createError(
        ErrorType.EMPLOYEE_NOT_FOUND.getMessage(),
        ErrorType.EMPLOYEE_NOT_FOUND.getType(),
        HttpStatus.NOT_FOUND.value()
      )
      .setUrl(request.getRequestURL().toString())
      .setRequestMethod(request.getMethod())
      .setTimestamp(Instant.now());

    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public ResponseEntity<ErrorEntity> handleConstraintException(
    HttpServletRequest request,
    DataIntegrityViolationException ex
  ) {
    ErrorEntity error = ErrorUtils
      .createError(
        ex.getMostSpecificCause().getMessage().split("Key")[1].trim(),
        ErrorType.DATA_INTEGRITY_VIOLATION.getType(),
        HttpStatus.UNPROCESSABLE_ENTITY.value()
      )
      .setUrl(request.getRequestURL().toString())
      .setRequestMethod(request.getMethod())
      .setTimestamp(Instant.now());
    return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
  }
}
