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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.epoch.activitysystem.configuration.constants.swagger.SwaggerConstants;
import com.epoch.activitysystem.entity.EmployeeEntity;
import com.epoch.activitysystem.exceptions.ErrorEntity;
import com.epoch.activitysystem.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "employee", description = "Registers all employees to the system")
@RestController
@RequestMapping("/v1/employee")
public class EmployeeController {

  @Autowired
  private EmployeeService service;

  @Operation(
    summary = "Creates an employee for the Epoch system",
    responses = {
      @ApiResponse(
        responseCode = SwaggerConstants.ENTITY_CREATED_CODE,
        description = SwaggerConstants.SUCCESS_CREATION_DESCRIPTION,
        content = {
          @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = EmployeeEntity.class)
          ),
          @Content(
            mediaType = MediaType.APPLICATION_XML_VALUE,
            schema = @Schema(implementation = EmployeeEntity.class)
          ),
        }
      ),
    }
  )
  @PostMapping(
    value = "",
    consumes = {
      MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,
    },
    produces = {
      MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,
    }
  )
  public ResponseEntity<EmployeeEntity> createEmployee(
    @Valid @RequestBody EmployeeEntity employee
  ) {
    return new ResponseEntity<>(service.save(employee), HttpStatus.CREATED);
  }

  @Operation(
    summary = "Retrieves an employee by id.",
    responses = {
      @ApiResponse(
        responseCode = SwaggerConstants.SUCCESS_CODE,
        description = SwaggerConstants.SUCCESS_DESCRIPTION,
        content = {
          @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = EmployeeEntity.class)
          ),
          @Content(
            mediaType = MediaType.APPLICATION_XML_VALUE,
            schema = @Schema(implementation = EmployeeEntity.class)
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
  @GetMapping(
    value = "/{id}",
    produces = {
      MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,
    }
  )
  public ResponseEntity<EmployeeEntity> getEmployeeById(
    @PathVariable("id") String id
  ) {
    return new ResponseEntity<>(
      service.findById(UUID.fromString(id)),
      HttpStatus.OK
    );
  }

  @Operation(
    summary = "Retrieves all employees.",
    responses = {
      @ApiResponse(
        responseCode = SwaggerConstants.SUCCESS_CODE,
        description = SwaggerConstants.SUCCESS_DESCRIPTION,
        content = {
          @Content(
            array = @ArraySchema(
              schema = @Schema(oneOf = { EmployeeEntity.class })
            )
          ),
        }
      ),
    }
  )
  @GetMapping(
    value = "",
    produces = {
      MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,
    }
  )
  public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
    return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
  }

  @Operation(
    summary = "Updates an employee by the employee's id.",
    responses = {
      @ApiResponse(
        responseCode = SwaggerConstants.SUCCESS_CODE,
        description = SwaggerConstants.ENTITY_UPDATED_DESCRIPTION,
        content = {
          @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = EmployeeEntity.class)
          ),
          @Content(
            mediaType = MediaType.APPLICATION_XML_VALUE,
            schema = @Schema(implementation = EmployeeEntity.class)
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
  @PutMapping(
    value = "/{id}",
    consumes = {
      MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,
    },
    produces = {
      MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,
    }
  )
  public ResponseEntity<EmployeeEntity> updateEmployeeById(
    @PathVariable("id") UUID id,
    @Valid @RequestBody EmployeeEntity employee
  ) {
    return new ResponseEntity<>(
      service.updateById(id, employee),
      HttpStatus.OK
    );
  }

  @Operation(
    summary = "Delete an employee by id",
    responses = {
      @ApiResponse(
        responseCode = SwaggerConstants.SUCCESS_CODE,
        description = SwaggerConstants.ENTITY_DELETED_DESCRIPTION,
        content = {
          @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            examples = @ExampleObject(
              description = "The client will receive a Map with a message property.",
              name = "message",
              value = "{ message: " +
              SwaggerConstants.ENTITY_DELETED_DESCRIPTION +
              " }"
            )
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
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Map<String, String>> deleteEmployeeById(
    @PathVariable("id") UUID id
  ) {
    return new ResponseEntity<>(service.deleteBy(id), HttpStatus.OK);
  }

  @Operation(
    summary = "Delete all employees",
    responses = {
      @ApiResponse(
        responseCode = SwaggerConstants.SUCCESS_CODE,
        description = SwaggerConstants.ENTITY_DELETED_DESCRIPTION,
        content = {
          @Content(
            examples = {
              @ExampleObject(
                description = "The client will receive a Map with a message property.",
                name = "message",
                value = "{ message: " +
                SwaggerConstants.ALL_ENTITITES_DELETED_DESCRIPTION +
                " }"
              ),
            }
          ),
        }
      ),
    }
  )
  @DeleteMapping(value = "")
  public ResponseEntity<Map<String, String>> deleteAllEmployees() {
    return new ResponseEntity<>(service.deleteAll(), HttpStatus.OK);
  }
}
