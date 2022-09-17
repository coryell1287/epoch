package com.epoch.activitysystem.exception;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epoch.activitysystem.configuration.constants.swagger.SwaggerConstants;
import com.epoch.activitysystem.controller.EmployeeController;
import com.epoch.activitysystem.exceptions.EmployeeNotFoundException;
import com.epoch.activitysystem.service.EmployeeService;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = { EmployeeController.class })
public class RestApiErrorHandlerTest {

  @MockBean
  private EmployeeService service;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldEmployeeNotFound() throws Exception {
    final UUID ID = UUID.randomUUID();

    when(service.findById(ID)).thenThrow(EmployeeNotFoundException.class);
    mockMvc
      .perform(get("/v1/employee/{id}", ID))
      .andExpect(status().isNotFound())
      .andExpect(
        jsonPath("$.message").value(SwaggerConstants.EMPLOYEE_NOT_FOUND)
      )
      .andDo(print());
  }
}
