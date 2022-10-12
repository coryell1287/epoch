package com.epoch.activitysystem.configuration.constants.swagger;

public interface SwaggerConstants {
  public static final String SUCCESS_CREATION_DESCRIPTION =
    "Entity created successfully";
  public static final String ENTITY_CREATED_CODE = "201";
  public static final String SUCCESS_CODE = "200";
  public static final String SUCCESS_DESCRIPTION = "Success with results";
  public static final String ENTITY_UPDATED_DESCRIPTION =
    "Entity successfully updated.";
  public static final String ENTITY_DELETED_CODE = "204";
  public static final String ENTITY_DELETED_DESCRIPTION = "Entity deleted successfully.";
  public static final String ALL_ENTITITES_DELETED_DESCRIPTION = "All entities deleted successfully.";
  public static final String EMPLOYEE_NOT_FOUND = "Employee not found";
  public static final String NOT_FOUND_CODE = "404";
  public static final String MESSAGE  = "message";
  public static final String MAP_DELETION_MESSAGE_DESCRIPTION = "Client will receive a Map with message property.";
  public static final String MAP_DELETION_VALUE = "{ message: " +
  SwaggerConstants.ALL_ENTITITES_DELETED_DESCRIPTION +
  " }";
  
}
