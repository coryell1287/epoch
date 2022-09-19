package com.epoch.activitysystem.entity;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class EmployeeEntityTest {
  
  @Test
  void createEmplyeeTest() {
    String emailAddress = "mail@outlook.com";
    String firstName = "John";
    String lastName = "Doe";
    Boolean onlineStatus = true;
    String role = "Manager";

    EmployeeEntity employeeEntity = EmployeeEntity
      .builder()
      .firstName(firstName)
      .emailAddress(emailAddress)
      .lastName(lastName)
      .onlineStatus(onlineStatus)
      .role(role)
      .build();

    assertAll(
      () -> assertEquals(emailAddress, employeeEntity.getEmailAddress()),
      () -> assertEquals(firstName, employeeEntity.getFirstName()),
      () -> assertEquals(lastName, employeeEntity.getLastName()),
      () -> assertEquals(onlineStatus, employeeEntity.getOnlineStatus()),
      () -> assertEquals(role, employeeEntity.getRole())
    );
  }
}
