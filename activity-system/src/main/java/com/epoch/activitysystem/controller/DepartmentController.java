package com.epoch.activitysystem.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.epoch.activitysystem.configuration.constants.swagger.SwaggerConstants;
import com.epoch.activitysystem.entity.DepartmentEntity;
import com.epoch.activitysystem.exceptions.ErrorEntity;
import com.epoch.activitysystem.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
  name = "department",
  description = "Department service will modify details about each employees as it relates the department the employe is in."
)
@RestController
@RequestMapping("/v1/department")
public class DepartmentController {

  @Autowired
  DepartmentService service;

  @Operation(
    summary = "Updates the employee's department information.",
    description = "Use this api for when all else fails and you need something to absolutely work.",
    responses = {
      @ApiResponse(
        responseCode = SwaggerConstants.SUCCESS_CREATION_DESCRIPTION,
        description = SwaggerConstants.SUCCESS_CREATION_DESCRIPTION,
        content = {
          @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = DepartmentEntity.class)
          ),
        }
      ),
    }
  )
  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<DepartmentEntity> addEmployeeToDepartment(
    @PathVariable("id") UUID id,
    @RequestBody DepartmentEntity department
  ) {
    return new ResponseEntity<>(
      service.updateById(id, department),
      HttpStatus.OK
    );
  }

  @Operation(
    summary = "Retrieves an employee along with department details",
    responses = {
      @ApiResponse(
        responseCode = SwaggerConstants.SUCCESS_CODE,
        description = SwaggerConstants.SUCCESS_DESCRIPTION,
        content = {
          @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = DepartmentEntity.class)
          ),
        }
      ),
      @ApiResponse(
        responseCode = SwaggerConstants.NOT_FOUND_CODE,
        description = SwaggerConstants.EMPLOYEE_NOT_FOUND,
        content = {
          @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ErrorEntity.class)
          ),
        }
      ),
    }
  )
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<DepartmentEntity> getEmployeeFromDepartmentByID(
    @Valid @PathVariable("id") UUID id
  ) {
    return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
  }

  @Operation(
    summary = "Returns all employees across every departmet",
    description = "When it doesn't matter which department",
    responses = {
      @ApiResponse(
        responseCode = SwaggerConstants.SUCCESS_CODE,
        description = SwaggerConstants.SUCCESS_DESCRIPTION,
        content = {
          @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            array = @ArraySchema(
              schema = @Schema(implementation = DepartmentEntity.class)
            )
          ),
        }
      ),
    }
  )
  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<DepartmentEntity>> getAllEmployeesFromAllDepartments() {
    return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
  }

  @Operation(
    summary = "Deletes all employees from the department.",
    description = "If there are orphaned employees in the department, this will remove them.",
    responses = {
      @ApiResponse(
        responseCode = SwaggerConstants.SUCCESS_CODE,
        description = SwaggerConstants.ENTITY_DELETED_DESCRIPTION,
        content = {
          @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            examples = {
              @ExampleObject(
                description = SwaggerConstants.MAP_DELETION_MESSAGE_DESCRIPTION,
                name = SwaggerConstants.MESSAGE,
                value = SwaggerConstants.MAP_DELETION_VALUE
              ),
            }
          ),
        }
      ),
    }
  )
  @DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, String>> deleteEmployeeFromDepartment() {
    return new ResponseEntity<>(service.deleteAll(), HttpStatus.OK);
  }
}
