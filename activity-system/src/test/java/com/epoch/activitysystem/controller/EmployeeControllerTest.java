package com.epoch.activitysystem.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epoch.activitysystem.configuration.constants.swagger.SwaggerConstants;
import com.epoch.activitysystem.entity.DepartmentEntity;
import com.epoch.activitysystem.entity.EmployeeEntity;
import com.epoch.activitysystem.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    final String firstName = "John";
    final String lastName = "Doe";
    final String userName = "doeman";
    final String emailAddress = "mail@outlook.com";
    final Boolean onlineStatus = false;

    final String departmentJobTitle = "Manager";
    final String departmentName = "Sales";
    final LocalDate departmentHireDate = LocalDate.now();
    final String departmentRegionName = "West Coast";

    DepartmentEntity department = DepartmentEntity
      .builder()
      .firstName(firstName)
      .lastName(lastName)
      .jobTitle(departmentJobTitle)
      .hireDate(departmentHireDate)
      .departmentName(departmentName)
      .regionName(departmentRegionName)
      .build();

    EmployeeEntity employeeEntity = EmployeeEntity
      .builder()
      .firstName(firstName)
      .lastName(lastName)
      .userName(userName)
      .onlineStatus(onlineStatus)
      .emailAddress(emailAddress)
      .department(department)
      .build();

    when(service.save(any(EmployeeEntity.class))).thenReturn(employeeEntity);
    mockMvc
      .perform(
        post("/v1/employee")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(employeeEntity))
      )
      .andExpect(jsonPath("$.firstName").value(firstName))
      .andExpect(jsonPath("$.lastName").value(lastName))
      .andExpect(jsonPath("$.userName").value(userName))
      .andExpect(jsonPath("$.onlineStatus").value(onlineStatus))
      .andExpect(jsonPath("$.emailAddress").value(emailAddress))
      .andExpect(status().isCreated())
      .andDo(print());
  }

  @Test
  void shouldReturnAnEmployeeById() throws Exception {
    final UUID ID = UUID.randomUUID();
    final String firstName = "John";
    final String lastName = "Doe";
    final String userName = "doeman";
    final String emailAddress = "mail@outlook.com";
    final Boolean onlineStatus = false;

    final String departmentJobTitle = "Manager";
    final String departmentName = "Sales";
    final LocalDate departmentHireDate = LocalDate.now();
    final String departmentRegionName = "West Coast";

    DepartmentEntity department = DepartmentEntity
      .builder()
      .firstName(firstName)
      .lastName(lastName)
      .jobTitle(departmentJobTitle)
      .hireDate(departmentHireDate)
      .departmentName(departmentName)
      .regionName(departmentRegionName)
      .build();

    EmployeeEntity employeeEntity = EmployeeEntity
      .builder()
      .id(ID)
      .firstName(firstName)
      .lastName(lastName)
      .userName(userName)
      .onlineStatus(onlineStatus)
      .emailAddress(emailAddress)
      .department(department)
      .build();

    when(service.findById(ID)).thenReturn(employeeEntity);
    mockMvc
      .perform(get("/v1/employee/{id}", ID))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id").value(ID.toString()))
      .andExpect(jsonPath("$.userName").value(userName))
      .andExpect(jsonPath("$.firstName").value(firstName))
      .andExpect(jsonPath("$.lastName").value(lastName))
      .andExpect(jsonPath("$.emailAddress").value(emailAddress))
      .andExpect(jsonPath("$.onlineStatus").value(onlineStatus))
      .andDo(print());
  }

  @Test
  void shouldReturnAListEmployees() throws Exception {
    final UUID ID = UUID.randomUUID();
    final String USERNAME = "joeUser";
    final String EMAILADDRESS = "mail@outlook.com";
    final String FIRSTNAME = "John";
    final String LASTNAME = "Doe";
    final Boolean ONLINESTATUS = true;

    final UUID ID2 = UUID.randomUUID();
    final String USERNAME2 = "samUser";
    final String EMAILADDRESS2 = "sam@outlook.com";
    final String FIRSTNAME2 = "Sam";
    final String LASTNAME2 = "Doe";
    final Boolean ONLINESTATUS2 = true;

    final UUID ID3 = UUID.randomUUID();
    final String USERNAME3 = "paulUser";
    final String EMAILADDRESS3 = "paul@outlook.com";
    final String FIRSTNAME3 = "Paul";
    final String LASTNAME3 = "Doe";
    final Boolean ONLINESTATUS3 = true;

    final String departmentJobTitle = "Manager";
    final String departmentName = "Sales";
    final LocalDate departmentHireDate = LocalDate.now();
    final String departmentRegionName = "West Coast";

    final String departmentJobTitle2 = "Accounting";
    final String departmentName2 = "Human Resource";
    final LocalDate departmentHireDate2 = LocalDate.now();
    final String departmentRegionName2 = "West Coast";

    final String departmentJobTitle3 = "Accounting";
    final String departmentName3 = "Human Resource";
    final LocalDate departmentHireDate3 = LocalDate.now();
    final String departmentRegionName3 = "West Coast";

    DepartmentEntity department1 = DepartmentEntity
      .builder()
      .firstName(FIRSTNAME)
      .lastName(LASTNAME)
      .jobTitle(departmentJobTitle)
      .hireDate(departmentHireDate)
      .departmentName(departmentName)
      .regionName(departmentRegionName)
      .build();

    DepartmentEntity department2 = DepartmentEntity
      .builder()
      .firstName(FIRSTNAME2)
      .lastName(LASTNAME2)
      .jobTitle(departmentJobTitle2)
      .hireDate(departmentHireDate2)
      .departmentName(departmentName2)
      .regionName(departmentRegionName2)
      .build();

    DepartmentEntity department3 = DepartmentEntity
      .builder()
      .firstName(FIRSTNAME3)
      .lastName(LASTNAME3)
      .jobTitle(departmentJobTitle3)
      .hireDate(departmentHireDate3)
      .departmentName(departmentName3)
      .regionName(departmentRegionName3)
      .build();

    EmployeeEntity employee1 = EmployeeEntity
      .builder()
      .id(ID)
      .userName(USERNAME)
      .firstName(FIRSTNAME)
      .lastName(LASTNAME)
      .department(department1)
      .emailAddress(EMAILADDRESS)
      .onlineStatus(ONLINESTATUS)
      .build();

    EmployeeEntity employee2 = EmployeeEntity
      .builder()
      .id(ID2)
      .userName(USERNAME2)
      .firstName(FIRSTNAME2)
      .lastName(LASTNAME2)
      .department(department2)
      .emailAddress(EMAILADDRESS2)
      .onlineStatus(ONLINESTATUS2)
      .build();

    EmployeeEntity employee3 = EmployeeEntity
      .builder()
      .id(ID3)
      .userName(USERNAME3)
      .firstName(FIRSTNAME3)
      .lastName(LASTNAME3)
      .department(department3)
      .emailAddress(EMAILADDRESS3)
      .onlineStatus(ONLINESTATUS3)
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
    final String FIRSTNAME = "John";
    final String LASTNAME = "Doe";

    final String USERNAME2 = "samUser";
    final String EMAILADDRESS2 = "sam@outlook.com";
    final Boolean ONLINESTATUS2 = true;

    final String departmentJobTitle = "Manager";
    final String departmentName = "Sales";
    final LocalDate departmentHireDate = LocalDate.now();
    final String departmentRegionName = "West Coast";

    DepartmentEntity department = DepartmentEntity
      .builder()
      .firstName(FIRSTNAME)
      .lastName(LASTNAME)
      .jobTitle(departmentJobTitle)
      .hireDate(departmentHireDate)
      .departmentName(departmentName)
      .regionName(departmentRegionName)
      .build();

    EmployeeEntity employeeEntity = EmployeeEntity
      .builder()
      .id(ID)
      .firstName(FIRSTNAME)
      .lastName(LASTNAME)
      .userName(USERNAME)
      .onlineStatus(ONLINESTATUS)
      .emailAddress(EMAILADDRESS)
      .department(department)
      .build();

    EmployeeEntity updatedEmployee = EmployeeEntity
      .builder()
      .userName(USERNAME2)
      .emailAddress(EMAILADDRESS2)
      .onlineStatus(ONLINESTATUS2)
      .build();

    when(service.findById(ID)).thenReturn(employeeEntity);
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

    Map<String, String> message = new HashMap<>();
    message.put("message", SwaggerConstants.ENTITY_DELETED_DESCRIPTION);

    when(service.deleteBy(ID)).thenReturn(message);
    mockMvc
      .perform(delete("/v1/employee/{id}", ID))
      .andExpect(status().isOk())
      .andExpect(
        jsonPath("$.message").value(SwaggerConstants.ENTITY_DELETED_DESCRIPTION)
      )
      .andDo(print());
  }

  @Test
  void shouldDeleteAllEmployess() throws Exception {
    Map<String, String> message = new HashMap<>();
    message.put("message", SwaggerConstants.ALL_ENTITITES_DELETED_DESCRIPTION);

    when(service.deleteAll()).thenReturn(message);
    mockMvc
      .perform(delete("/v1/employee"))
      .andExpect(status().isOk())
      .andExpect(
        jsonPath("$.message")
          .value(SwaggerConstants.ALL_ENTITITES_DELETED_DESCRIPTION)
      )
      .andDo(print());
  }
}
