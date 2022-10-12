package com.epoch.activitysystem.entity;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class DepartmentEntityTest {

  @Test
  void shouldCreateDepartmentEntity() {
    final String firstName = "Amy";
    final String lastName = "Bower";
    final String jobTitle = "Manager";
    final LocalDate hireDate = LocalDate.now();
    final String regionName = "West Coast";
    final String departmentName = "Accounting";
    

    DepartmentEntity department = DepartmentEntity
      .builder()
      .firstName(firstName)
      .lastName(lastName)
      .jobTitle(jobTitle)
      .hireDate(hireDate)
      .departmentName(departmentName)
      .regionName(regionName)
      .build();

    assertAll(
      () -> assertEquals(firstName, department.getFirstName()),
      () -> assertEquals(lastName, department.getLastName()),
      () -> assertEquals(jobTitle, department.getJobTitle()),
      () -> assertEquals(hireDate, department.getHireDate()),
      () -> assertEquals(regionName, department.getRegionName()),
      () -> assertEquals(department.getDepartmentName(), departmentName)
    );
  }
}
