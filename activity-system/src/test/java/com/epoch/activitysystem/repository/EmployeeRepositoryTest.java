package com.epoch.activitysystem.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epoch.activitysystem.entity.DepartmentEntity;
import com.epoch.activitysystem.entity.EmployeeEntity;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@Transactional
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

    EmployeeEntity employee = repository.save(employeeEntity);

    assertAll(
      () -> assertEquals(userName, employee.getUserName()),
      () -> assertEquals(firstName, employee.getFirstName()),
      () -> assertEquals(lastName, employee.getLastName()),
      () -> assertEquals(emailAddress, employee.getEmailAddress()),
      () -> assertEquals(onlineStatus, employee.getOnlineStatus()),
      () -> assertEquals(department, employee.getDepartment())
    );
  }

  @Test
  void should_find_all_employees() {
    final String USERNAME = "joeUser2";
    final String EMAILADDRESS = "mail2@outlook.com";
    final String FIRSTNAME = "John";
    final String LASTNAME = "Doe";
    final Boolean ONLINESTATUS = true;

    final String USERNAME2 = "morenoUser2";
    final String EMAILADDRESS2 = "moreno2@mail.com";
    final String FIRSTNAME2 = "Francesco";
    final String LASTNAME2 = "Moreno";
    final Boolean ONLINESTATUS2 = false;

    final String departmentFirstName1 = "John";
    final String departmentLastName1 = "Doe";
    final String departmentJobTitle1 = "Manager";
    final String departmentName1 = "Accounting";
    final LocalDate departmentHireDate1 = LocalDate.now();
    final String departmentRegionName1 = "West Coast";

    final String departmentFirstName2 = "Francesco";
    final String departmentLastName2 = "Moreno";
    final String departmentJobTitle2 = "CEO";
    final String departmentName2 = "Sales";
    final LocalDate departmentHireDate2 = LocalDate.now();
    final String departmentRegionName2 = "East Coast";

    DepartmentEntity department1 = DepartmentEntity
      .builder()
      .firstName(departmentFirstName1)
      .lastName(departmentLastName1)
      .jobTitle(departmentJobTitle1)
      .departmentName(departmentName1)
      .hireDate(departmentHireDate1)
      .regionName(departmentRegionName1)
      .build();

    DepartmentEntity department2 = DepartmentEntity
      .builder()
      .firstName(departmentFirstName2)
      .lastName(departmentLastName2)
      .departmentName(departmentName2)
      .jobTitle(departmentJobTitle2)
      .hireDate(departmentHireDate2)
      .regionName(departmentRegionName2)
      .build();

    EmployeeEntity employeeEntity1 = EmployeeEntity
      .builder()
      .department(department1)
      .userName(USERNAME)
      .firstName(FIRSTNAME)
      .lastName(LASTNAME)
      .department(department1)
      .emailAddress(EMAILADDRESS)
      .onlineStatus(ONLINESTATUS)
      .build();

    repository.save(employeeEntity1);

    EmployeeEntity employeeEntity2 = EmployeeEntity
      .builder()
      .userName(USERNAME2)
      .firstName(FIRSTNAME2)
      .lastName(LASTNAME2)
      .department(department2)
      .emailAddress(EMAILADDRESS2)
      .onlineStatus(ONLINESTATUS2)
      .build();

    repository.save(employeeEntity2);
    List<EmployeeEntity> employees = repository.findAll();

    assertThat(employees).hasSize(2).contains(employeeEntity1, employeeEntity2);
  }

  @Test
  void should_find_employe_by_id() {
    final String USERNAME = "joeUser";
    final String EMAILADDRESS = "mail@outlook.com";
    final String FIRSTNAME = "John";
    final String LASTNAME = "Doe";
    final Boolean ONLINESTATUS = true;

    final String departmentFirstName = "John";
    final String departmentLastName = "Doe";
    final String departmentJobTitle = "Manager";
    final String departmentName = "Accounting";
    final LocalDate departmentHireDate = LocalDate.now();
    final String departmentRegionName = "West Coast";

    DepartmentEntity department = DepartmentEntity
      .builder()
      .firstName(departmentFirstName)
      .lastName(departmentLastName)
      .jobTitle(departmentJobTitle)
      .departmentName(departmentName)
      .hireDate(departmentHireDate)
      .regionName(departmentRegionName)
      .build();

    EmployeeEntity employeeEntity = EmployeeEntity
      .builder()
      .userName(USERNAME)
      .firstName(FIRSTNAME)
      .lastName(LASTNAME)
      .department(department)
      .emailAddress(EMAILADDRESS)
      .onlineStatus(ONLINESTATUS)
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
    final String LASTNAME = "Doe";
    final Boolean ONLINESTATUS = true;

    final String departmentFirstName = "John";
    final String departmentLastName = "Doe";
    final String departmentJobTitle = "Manager";
    final String departmentName = "Sales";
    final LocalDate departmentHireDate = LocalDate.now();
    final String departmentRegionName = "West Coast";

    final String EMAILADDRESS2 = "paul@outlook.com";
    final Boolean ONLINESTATUS2 = false;

    DepartmentEntity department = DepartmentEntity
      .builder()
      .firstName(departmentFirstName)
      .lastName(departmentLastName)
      .jobTitle(departmentJobTitle)
      .departmentName(departmentName)
      .hireDate(departmentHireDate)
      .regionName(departmentRegionName)
      .build();

    EmployeeEntity employeeEntity = EmployeeEntity
      .builder()
      .userName(USERNAME)
      .firstName(FIRSTNAME)
      .lastName(LASTNAME)
      .department(department)
      .emailAddress(EMAILADDRESS)
      .onlineStatus(ONLINESTATUS)
      .build();

    repository.save(employeeEntity);

    EmployeeEntity employee = repository.findById(employeeEntity.getId()).get();

    employee.setEmailAddress(EMAILADDRESS2).setOnlineStatus(ONLINESTATUS2);

    EmployeeEntity updatedEmployee = repository.save(employee);

    assertAll(
      () -> assertEquals(EMAILADDRESS2, updatedEmployee.getEmailAddress()),
      () -> assertEquals(ONLINESTATUS2, updatedEmployee.getOnlineStatus())
    );
  }

  @Test
  void should_delete_employee_by_id() {
    final String USERNAME = "joeUser";
    final String EMAILADDRESS = "mail@outlook.com";
    final String FIRSTNAME = "John";
    final String LASTNAME = "Doe";
    final Boolean ONLINESTATUS = true;

    final String departmentFirstName = "John";
    final String departmentLastName = "Doe";
    final String departmentName = "Sales";
    final String departmentJobTitle = "Manager";
    final LocalDate departmentHireDate = LocalDate.now();
    final String departmentRegionName = "West Coast";

    DepartmentEntity department = DepartmentEntity
      .builder()
      .firstName(departmentFirstName)
      .lastName(departmentLastName)
      .departmentName(departmentName)
      .jobTitle(departmentJobTitle)
      .hireDate(departmentHireDate)
      .regionName(departmentRegionName)
      .build();

    EmployeeEntity employee = EmployeeEntity
      .builder()
      .userName(USERNAME)
      .emailAddress(EMAILADDRESS)
      .firstName(FIRSTNAME)
      .lastName(LASTNAME)
      .department(department)
      .onlineStatus(ONLINESTATUS)
      .build();

    EmployeeEntity saveEmployee = repository.save(employee);

    assertThat(saveEmployee).isNotNull();
    repository.deleteById(saveEmployee.getId());
    assertThat(repository.findById(saveEmployee.getId())).isEmpty();
  }

  @Test
  void should_delete_all_employees() {
    final String USERNAME = "joeUser";
    final String EMAILADDRESS = "mail@outlook.com";
    final String FIRSTNAME = "John";
    final String LASTNAME = "Doe";
    final Boolean ONLINESTATUS = true;

    final String USERNAME2 = "morenoUser";
    final String EMAILADDRESS2 = "moreno2@mail.com";
    final String FIRSTNAME2 = "Francesco";
    final String LASTNAME2 = "Moreno";
    final Boolean ONLINESTATUS2 = false;

    final String departmentFirstName1 = "John";
    final String departmentLastName1 = "Doe";
    final String departmentJobTitle1 = "Manager";
    final String departmentName1 = "Accounting";
    final LocalDate departmentHireDate1 = LocalDate.now();
    final String departmentRegionName1 = "West Coast";

    final String departmentFirstName2 = "Francesco";
    final String departmentLastName2 = "Moreno";
    final String departmentJobTitle2 = "CEO";
    final String departmentName2 = "Sales";
    final LocalDate departmentHireDate2 = LocalDate.now();
    final String departmentRegionName2 = "East Coast";

    DepartmentEntity department1 = DepartmentEntity
      .builder()
      .firstName(departmentFirstName1)
      .lastName(departmentLastName1)
      .jobTitle(departmentJobTitle1)
      .departmentName(departmentName1)
      .hireDate(departmentHireDate1)
      .regionName(departmentRegionName1)
      .build();

    DepartmentEntity department2 = DepartmentEntity
      .builder()
      .firstName(departmentFirstName2)
      .lastName(departmentLastName2)
      .departmentName(departmentName2)
      .jobTitle(departmentJobTitle2)
      .hireDate(departmentHireDate2)
      .regionName(departmentRegionName2)
      .build();


    EmployeeEntity employeeEntity1 = EmployeeEntity
      .builder()
      .userName(USERNAME)
      .firstName(FIRSTNAME)
      .lastName(LASTNAME)
      .department(department1)
      .emailAddress(EMAILADDRESS)
      .onlineStatus(ONLINESTATUS)
      .build();

    EmployeeEntity employeeEntity2 = EmployeeEntity
      .builder()
      .userName(USERNAME2)
      .firstName(FIRSTNAME2)
      .lastName(LASTNAME2)
      .department(department2)
      .emailAddress(EMAILADDRESS2)
      .onlineStatus(ONLINESTATUS2)
      .build();

    repository.save(employeeEntity1);
    repository.save(employeeEntity2);
  
    repository.deleteAll();
    assertThat(repository.findAll()).hasSize(0);
  }
}
