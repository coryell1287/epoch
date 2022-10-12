package com.epoch.activitysystem.entity;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class EmployeeEntityTest {

  @Test
  void createEmplyeeTest() {
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

    assertAll(
      () -> assertEquals(firstName, employeeEntity.getFirstName()),
      () -> assertEquals(lastName, employeeEntity.getLastName()),
      () -> assertEquals(userName, employeeEntity.getUserName()),
      () -> assertEquals(emailAddress, employeeEntity.getEmailAddress()),
      () -> assertEquals(onlineStatus, employeeEntity.getOnlineStatus()),
      () -> assertEquals(department, employeeEntity.getDepartment()),
      () -> assertEquals(departmentName, employeeEntity.getDepartment().getDepartmentName()),
      () -> assertEquals(departmentJobTitle, employeeEntity.getDepartment().getJobTitle()),
      () -> assertEquals(departmentHireDate, employeeEntity.getDepartment().getHireDate()),
      () -> assertEquals(departmentRegionName, employeeEntity.getDepartment().getRegionName())
    );
  }
}
