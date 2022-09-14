package com.epoch.activitysystem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epoch.activitysystem.configuration.constants.swagger.SwaggerConstants;
import com.epoch.activitysystem.entity.EmployeeEntity;
import com.epoch.activitysystem.exceptions.EmployeeNotFoundException;
import com.epoch.activitysystem.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

  @MockBean
  private EmployeeService service;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void shouldCreateAnEmployee() throws Exception {
    final String USERNAME = "joeUser";
    final String EMAILADDRESS = "mail@outlook.com";
    final String FIRSTNAME = "John";
    final String LASTNAME = "Doe";
    final Boolean ONLINESTATUS = true;
    final String ROLE = "Manager";

    EmployeeEntity employeeEntity = EmployeeEntity
      .builder()
      .userName(USERNAME)
      .firstName(FIRSTNAME)
      .lastName(LASTNAME)
      .emailAddress(EMAILADDRESS)
      .onlineStatus(ONLINESTATUS)
      .role(ROLE)
      .build();

    mockMvc
      .perform(
        post("/v1/employee")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(employeeEntity))
      )
      .andExpect(status().isCreated())
      .andDo(print());
  }

  @Test
  void shouldReturnAnEmployeeById() throws Exception {
    final UUID ID = UUID.randomUUID();
    final String USERNAME = "joeUser";
    final String EMAILADDRESS = "mail@outlook.com";
    final String FIRSTNAME = "John";
    final UUID DEPARTMENTID = UUID.randomUUID();
    final String LASTNAME = "Doe";
    final Boolean ONLINESTATUS = true;
    final String ROLE = "Manager";

    EmployeeEntity employeeEntity = EmployeeEntity
      .builder()
      .id(ID)
      .userName(USERNAME)
      .firstName(FIRSTNAME)
      .lastName(LASTNAME)
      .departmentID(DEPARTMENTID)
      .emailAddress(EMAILADDRESS)
      .onlineStatus(ONLINESTATUS)
      .role(ROLE)
      .build();

    when(service.findById(ID)).thenReturn(employeeEntity);
    mockMvc
      .perform(get("/v1/employee/{id}", ID))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id").value(ID.toString()))
      .andExpect(jsonPath("$.userName").value(employeeEntity.getUserName()))
      .andExpect(jsonPath("$.firstName").value(employeeEntity.getFirstName()))
      .andExpect(jsonPath("$.lastName").value(employeeEntity.getLastName()))
      .andExpect(jsonPath("$.departmentID").value(DEPARTMENTID.toString()))
      .andExpect(
        jsonPath("$.emailAddress").value(employeeEntity.getEmailAddress())
      )
      .andExpect(
        jsonPath("$.onlineStatus").value(employeeEntity.getOnlineStatus())
      )
      .andExpect(jsonPath("$.role").value(employeeEntity.getRole()))
      .andDo(print());
  }

  @Test
  void shouldReturnAListEmployees() throws Exception {
    final UUID ID = UUID.randomUUID();
    final String USERNAME = "joeUser";
    final String EMAILADDRESS = "mail@outlook.com";
    final String FIRSTNAME = "John";
    final UUID DEPARTMENTID = UUID.randomUUID();
    final String LASTNAME = "Doe";
    final Boolean ONLINESTATUS = true;
    final String ROLE = "Manager";

    final UUID ID2 = UUID.randomUUID();
    final String USERNAME2 = "samUser";
    final String EMAILADDRESS2 = "sam@outlook.com";
    final String FIRSTNAME2 = "Sam";
    final UUID DEPARTMENTID2 = UUID.randomUUID();
    final String LASTNAME2 = "Doe";
    final Boolean ONLINESTATUS2 = true;
    final String ROLE2 = "Supervisor";

    final UUID ID3 = UUID.randomUUID();
    final String USERNAME3 = "paulUser";
    final String EMAILADDRESS3 = "paul@outlook.com";
    final String FIRSTNAME3 = "Paul";
    final UUID DEPARTMENTID3 = UUID.randomUUID();
    final String LASTNAME3 = "Doe";
    final Boolean ONLINESTATUS3 = true;
    final String ROLE3 = "Clerk";

    EmployeeEntity employee1 = EmployeeEntity
      .builder()
      .id(ID)
      .userName(USERNAME)
      .firstName(FIRSTNAME)
      .lastName(LASTNAME)
      .departmentID(DEPARTMENTID)
      .emailAddress(EMAILADDRESS)
      .onlineStatus(ONLINESTATUS)
      .role(ROLE)
      .build();

    EmployeeEntity employee2 = EmployeeEntity
      .builder()
      .id(ID2)
      .userName(USERNAME2)
      .firstName(FIRSTNAME2)
      .lastName(LASTNAME2)
      .departmentID(DEPARTMENTID2)
      .emailAddress(EMAILADDRESS2)
      .onlineStatus(ONLINESTATUS2)
      .role(ROLE2)
      .build();

    EmployeeEntity employee3 = EmployeeEntity
      .builder()
      .id(ID3)
      .userName(USERNAME3)
      .firstName(FIRSTNAME3)
      .lastName(LASTNAME3)
      .departmentID(DEPARTMENTID3)
      .emailAddress(EMAILADDRESS3)
      .onlineStatus(ONLINESTATUS3)
      .role(ROLE3)
      .build();

    List<EmployeeEntity> employees = new ArrayList<>();

    employees.add(employee1);
    employees.add(employee2);
    employees.add(employee3);

    when(service.findAll()).thenReturn(employees);
    mockMvc
      .perform(get("/v1/employee"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.size()").value(employees.size()))
      .andDo(print());
  }

  @Test
  void shouldUpdateEmployeeById() throws Exception {
    final UUID ID = UUID.randomUUID();
    final String USERNAME = "joeUser";
    final String EMAILADDRESS = "mail@outlook.com";
    final Boolean ONLINESTATUS = true;
    final String ROLE = "Manager";

    final String USERNAME2 = "samUser";
    final String EMAILADDRESS2 = "sam@outlook.com";
    final Boolean ONLINESTATUS2 = true;
    final String ROLE2 = "Supervisor";

    EmployeeEntity employee1 = EmployeeEntity
      .builder()
      .userName(USERNAME)
      .emailAddress(EMAILADDRESS)
      .onlineStatus(ONLINESTATUS)
      .role(ROLE)
      .build();

    EmployeeEntity updatedEmployee = EmployeeEntity
      .builder()
      .userName(USERNAME2)
      .emailAddress(EMAILADDRESS2)
      .onlineStatus(ONLINESTATUS2)
      .role(ROLE2)
      .build();

    when(service.findById(ID)).thenReturn(employee1);
    when(service.save(any(EmployeeEntity.class))).thenReturn(updatedEmployee);
    mockMvc
      .perform(
        put("/v1/employee/{id}", ID)
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(updatedEmployee))
      )
      .andExpect(status().isOk())
      .andDo(print());
  }

  @Test
  void shouldDeleteEmployeeById() throws Exception {
    final UUID ID = UUID.randomUUID();

    doNothing().when(service).deleteBy(ID);

    mockMvc
      .perform(delete("/v1/employee/{id}", ID))
      .andExpect(status().isNoContent())
      .andDo(print());
  }

  @Test
  void shouldDeleteAllEmployess() throws Exception {
    doNothing().when(service).deleteAll();

    mockMvc
      .perform(delete("/v1/employee"))
      .andExpect(status().isNoContent())
      .andDo(print());
  }

  @Test
  void shouldReturnEmployeeNotFoundException() throws Exception {
    final UUID ID = UUID.randomUUID();
    EmployeeNotFoundException exception = assertThrows(
      EmployeeNotFoundException.class,
      () -> {
        when(service.findById(ID)).thenThrow(EmployeeNotFoundException.class);
      }
    );

    // when(service.findById(ID)).thenThrow(EmployeeNotFoundException.class);
    assertEquals(SwaggerConstants.EMPLOYEE_NOT_FOUND, exception.getMessage());
    mockMvc
      .perform(get("/v1/employee/{id}", ID))
      .andExpect(status().isNotFound())
      .andDo(print());
  }
}
