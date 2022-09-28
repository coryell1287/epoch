package com.epoch.activitysystem.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.epoch.activitysystem.entity.EmployeeEntity;

@DataJpaTest
public class EmployeeRepositoryTest {

  @Autowired
  private EmployeeRepository repository;

  @Test
  void should_return_no_employees_if_repository_is_empty() {
    List<EmployeeEntity> employees = repository.findAll();

    assertTrue(employees.isEmpty());
  }

  @Test
  void should_store_an_employee() {
    final String USERNAME = "joeUser";
    final String EMAILADDRESS = "mail@outlook.com";
    final UUID DEPARTMENTID = UUID.randomUUID();
    final String DEPARTMENT = "Marketing";
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
      .departmentID(DEPARTMENTID)
      .department(DEPARTMENT)
      .onlineStatus(ONLINESTATUS)
      .role(ROLE)
      .build();

    EmployeeEntity employee = repository.save(employeeEntity);

    assertEquals(USERNAME, employee.getUserName());
    assertEquals(FIRSTNAME, employee.getFirstName());
    assertEquals(LASTNAME, employee.getLastName());
    assertEquals(EMAILADDRESS, employee.getEmailAddress());
    assertEquals(DEPARTMENTID, employee.getDepartmentID());
    assertEquals(DEPARTMENT, employee.getDepartment());
    assertEquals(ONLINESTATUS, employee.getOnlineStatus());
    assertEquals(ROLE, employee.getRole());
  }

  @Test
  void should_find_all_employees() {
    final String DEPARTMENT = "Marketing";
    final UUID DEPARTMENTID = UUID.randomUUID();
    final String USERNAME = "joeUser2";
    final String EMAILADDRESS = "mail2@outlook.com";
    final String FIRSTNAME = "John";
    final String LASTNAME = "Doe";
    final Boolean ONLINESTATUS = true;
    final String ROLE = "Manager";

    final String DEPARTMENT2 = "HR";
    final UUID DEPARTMENTID2 = UUID.randomUUID();
    final String USERNAME2 = "morenoUser2";
    final String EMAILADDRESS2 = "moreno2@mail.com";
    final String FIRSTNAME2 = "Francesco";
    final String LASTNAME2 = "Moreno";
    final Boolean ONLINESTATUS2 = false;
    final String ROLE2 = "Deputy";

    EmployeeEntity employeeEntity1 = EmployeeEntity
      .builder()
      .department(DEPARTMENT)
      .departmentID(DEPARTMENTID)
      .userName(USERNAME)
      .firstName(FIRSTNAME)
      .lastName(LASTNAME)
      .emailAddress(EMAILADDRESS)
      .onlineStatus(ONLINESTATUS)
      .role(ROLE)
      .build();

    repository.save(employeeEntity1);

    EmployeeEntity employeeEntity2 = EmployeeEntity
      .builder()
      .userName(USERNAME2)
      .firstName(FIRSTNAME2)
      .lastName(LASTNAME2)
      .department(DEPARTMENT2)
      .departmentID(DEPARTMENTID2)
      .emailAddress(EMAILADDRESS2)
      .onlineStatus(ONLINESTATUS2)
      .role(ROLE2)
      .build();

    repository.save(employeeEntity2);

    List<EmployeeEntity> employees = repository.findAll();

    assertThat(employees).hasSize(2).contains(employeeEntity1, employeeEntity2);
  }

  @Test
  void should_find_employe_by_id() {
    final String USERNAME = "joeUser";
    final String EMAILADDRESS = "mail@outlook.com";
    final String DEPARTMENT = "Marketing";
    final String FIRSTNAME = "John";
    final String LASTNAME = "Doe";
    final Boolean ONLINESTATUS = true;
    final String ROLE = "Manager";

    EmployeeEntity employeeEntity = EmployeeEntity
      .builder()
      .userName(USERNAME)
      .firstName(FIRSTNAME)
      .lastName(LASTNAME)
      .department(DEPARTMENT)
      .emailAddress(EMAILADDRESS)
      .onlineStatus(ONLINESTATUS)
      .role(ROLE)
      .build();

    repository.save(employeeEntity);
    Optional<EmployeeEntity> employee = repository.findById(
      employeeEntity.getId()
    );

    assertThat(employee.get()).isEqualTo(employeeEntity);
  }

  @Test
  void should_update_employee_by_id() {
    final String USERNAME = "joeUser";
    final String EMAILADDRESS = "mail@outlook.com";
    final String FIRSTNAME = "John";
    final String DEPARTMENT = "Marketing";
    final String LASTNAME = "Doe";
    final Boolean ONLINESTATUS = true;
    final String ROLE = "Manager";

    final String EMAILADDRESS2 = "paul@outlook.com";
    final Boolean ONLINESTATUS2 = false;
    final String ROLE2 = "Boss";

    EmployeeEntity employeeEntity = EmployeeEntity
      .builder()
      .userName(USERNAME)
      .firstName(FIRSTNAME)
      .lastName(LASTNAME)
      .department(DEPARTMENT)
      .emailAddress(EMAILADDRESS)
      .onlineStatus(ONLINESTATUS)
      .role(ROLE)
      .build();

    repository.save(employeeEntity);

    EmployeeEntity employee = repository.findById(employeeEntity.getId()).get();

    employee
      .setEmailAddress(EMAILADDRESS2)
      .setOnlineStatus(ONLINESTATUS2)
      .setRole(ROLE2);

    EmployeeEntity updatedEmployee = repository.save(employee);

    assertEquals(EMAILADDRESS2, updatedEmployee.getEmailAddress());
    assertEquals(ONLINESTATUS2, updatedEmployee.getOnlineStatus());
    assertEquals(ROLE2, updatedEmployee.getRole());
  }

  @Test
  void should_delete_employee_by_id() {
    final String USERNAME = "joeUser";
    final String DEPARTMENT = "Marketing";
    final String EMAILADDRESS = "mail@outlook.com";
    final String FIRSTNAME = "John";
    final String LASTNAME = "Doe";
    final Boolean ONLINESTATUS = true;
    final String ROLE = "Manager";

    EmployeeEntity employee = EmployeeEntity
      .builder()
      .userName(USERNAME)
      .emailAddress(EMAILADDRESS)
      .firstName(FIRSTNAME)
      .lastName(LASTNAME)
      .department(DEPARTMENT)
      .onlineStatus(ONLINESTATUS)
      .role(ROLE)
      .build();

    EmployeeEntity saveEmployee = repository.save(employee);

    assertThat(saveEmployee).isNotNull();

    repository.deleteById(saveEmployee.getId());

    assertThat(repository.findById(saveEmployee.getId())).isEmpty();
  }

  // @Test
  // void should_delete_all_employees() {
  //   final String USERNAME = "joeUser";
  //   final String EMAILADDRESS = "mail@outlook.com";
  //   final String FIRSTNAME = "John";
  //   final String LASTNAME = "Doe";
  //   final String DEPARTMENT = "Marketing";
  //   final Boolean ONLINESTATUS = true;
  //   final String ROLE = "Manager";

  //   final String USERNAME2 = "morenoUser";
  //   final String EMAILADDRESS2 = "moreno2@mail.com";
  //   final String DEPARTMENT2 = "HR";
  //   final String FIRSTNAME2 = "Francesco";
  //   final String LASTNAME2 = "Moreno";
  //   final Boolean ONLINESTATUS2 = false;
  //   final String ROLE2 = "Deputy";

  //   EmployeeEntity employeeEntity1 = EmployeeEntity
  //     .builder()
  //     .userName(USERNAME)
  //     .firstName(FIRSTNAME)
  //     .lastName(LASTNAME)
  //     .department(DEPARTMENT)
  //     .emailAddress(EMAILADDRESS)
  //     .onlineStatus(ONLINESTATUS)
  //     .role(ROLE)
  //     .build();

  //   EmployeeEntity employeeEntity2 = EmployeeEntity
  //     .builder()
  //     .userName(USERNAME2)
  //     .firstName(FIRSTNAME2)
  //     .lastName(LASTNAME2)
  //     .department(DEPARTMENT2)
  //     .emailAddress(EMAILADDRESS2)
  //     .onlineStatus(ONLINESTATUS2)
  //     .role(ROLE2)
  //     .build();

  //   // repository.save(employeeEntity1);
  //   // repository.save(employeeEntity2);
  //   entityManager.persist(employeeEntity1);
  //   entityManager.persist(employeeEntity2);
  //   repository.deleteAll();
  //   assertThat(repository.findAll()).hasSize(0);
  // }

  @Test
  void should_return_false_if_username_already_exists() {}

  @Test
  void should_insert_user_if_username_does_not_exists() {}
}
