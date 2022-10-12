package com.epoch.activitysystem.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epoch.activitysystem.entity.DepartmentEntity;
import java.time.LocalDate;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@Transactional
@DataJpaTest
public class DepartmentRepositoryTest {

  @Autowired
  DepartmentRepository departmentRepository;

  @Test
  void should_return_no_employees_from_department_when_empty() {
    List<DepartmentEntity> department = departmentRepository.findAll();

    assertTrue(department.isEmpty());
  }

  @Test
  void should_store_employee_in_department() {
    final String firstName = "John";
    final String lastName = "Doe";
    final String departmentName = "Accounting";
    final String jobTitle = "Manager";
    final LocalDate hireDate = LocalDate.now();
    final String regionName = "West Coast";

    DepartmentEntity department = DepartmentEntity
      .builder()
      .firstName(firstName)
      .lastName(lastName)
      .jobTitle(jobTitle)
      .hireDate(hireDate)
      .departmentName(departmentName)
      .regionName(regionName)
      .build();

    DepartmentEntity departmentResponse = departmentRepository.save(department);

    assertAll(
      () -> assertEquals(firstName, departmentResponse.getFirstName()),
      () -> assertEquals(lastName, departmentResponse.getLastName()),
      () -> assertEquals(jobTitle, departmentResponse.getJobTitle()),
      () -> assertEquals(hireDate, departmentResponse.getHireDate()),
      () -> assertEquals(regionName, departmentResponse.getRegionName()),
      () -> assertEquals(departmentName, departmentResponse.getDepartmentName())
    );
  }

  @Test
  void should_return_all_employees_from_the_department() {
    final String departmentFirstName1 = "John";
    final String departmentLastName1 = "Doe";
    final String departmentJobTitle1 = "Manager";
    final LocalDate departmentHireDate1 = LocalDate.now();
    final String departmentRegionName1 = "West Coast";

    final String departmentFirstName2 = "Francesco";
    final String departmentLastName2 = "Moreno";
    final String departmentJobTitle2 = "CEO";
    final LocalDate departmentHireDate2 = LocalDate.now();
    final String departmentRegionName2 = "East Coast";

    DepartmentEntity department1 = DepartmentEntity
      .builder()
      .firstName(departmentFirstName1)
      .lastName(departmentLastName1)
      .jobTitle(departmentJobTitle1)
      .hireDate(departmentHireDate1)
      .regionName(departmentRegionName1)
      .build();

    departmentRepository.save(department1);

    DepartmentEntity department2 = DepartmentEntity
      .builder()
      .firstName(departmentFirstName2)
      .lastName(departmentLastName2)
      .jobTitle(departmentJobTitle2)
      .hireDate(departmentHireDate2)
      .regionName(departmentRegionName2)
      .build();

    departmentRepository.save(department2);

    List<DepartmentEntity> departmentEmployees = departmentRepository.findAll();

    assertThat(departmentEmployees)
      .hasSize(2)
      .contains(department1, department2);
  }

  @Test
  void should_update_employee_in_department() {
    final String departmentFirstName = "John";
    final String departmentLastName = "Doe";
    final String departmentJobTitle = "Manager";
    final LocalDate departmentHireDate = LocalDate.now();
    final String departmentRegionName = "West Coast";
    final String updatedJobTitle = "Boss";
    final String updatedRegion = "Southern Region";

    DepartmentEntity department = DepartmentEntity
      .builder()
      .firstName(departmentFirstName)
      .lastName(departmentLastName)
      .jobTitle(departmentJobTitle)
      .hireDate(departmentHireDate)
      .regionName(departmentRegionName)
      .build();

    departmentRepository.save(department);

    DepartmentEntity updatedDepartment = departmentRepository
      .findById(department.getId())
      .get();

    updatedDepartment.setJobTitle(updatedJobTitle).setRegionName(updatedRegion);

    departmentRepository.save(updatedDepartment);

    DepartmentEntity departmentResult = departmentRepository
      .findById(department.getId())
      .get();

    assertAll(
      () -> assertEquals(updatedJobTitle, departmentResult.getJobTitle()),
      () -> assertEquals(updatedRegion, departmentResult.getRegionName())
    );
  }

  @Test
  void should_delete_department_employee_by_id() {
    final String firstName = "John";
    final String lastName = "Doe";
    final String departmentJobTitle = "Manager";
    final LocalDate departmentHireDate = LocalDate.now();
    final String departmentRegionName = "West Coast";

    DepartmentEntity department = DepartmentEntity
      .builder()
      .firstName(firstName)
      .lastName(lastName)
      .jobTitle(departmentJobTitle)
      .hireDate(departmentHireDate)
      .regionName(departmentRegionName)
      .build();

    departmentRepository.save(department);

    assertThat(departmentRepository.findById(department.getId()).get())
      .isNotNull();
    departmentRepository.deleteById(department.getId());
    assertThat(departmentRepository.findById(department.getId())).isEmpty();
  }
  //   @Test
  //   void should_delete_all_employees_in_department() {
  //     final String userName1 = "joeUser2";
  //     final String firstName1 = "John";
  //     final String lastName1 = "Doe";
  //     final String emailAddress1 = "mail2@outlook.com";
  //     final Boolean onlineStatus1 = true;

  //     final String userName2 = "morenoUser2";
  //     final String firstName2 = "Francesco";
  //     final String lastName2 = "Moreno";
  //     final String emailAddress2 = "moreno2@mail.com";
  //     final Boolean onlineStatus2 = false;

  //     final String departmentFirstName1 = "John";
  //     final String departmentLastName1 = "Doe";
  //     final String departmentJobTitle1 = "Manager";
  //     final LocalDate departmentHireDate1 = LocalDate.now();
  //     final String departmentRegionName1 = "West Coast";

  //     final String departmentFirstName2 = "Francesco";
  //     final String departmentLastName2 = "Moreno";
  //     final String departmentJobTitle2 = "CEO";
  //     final LocalDate departmentHireDate2 = LocalDate.now();
  //     final String departmentRegionName2 = "East Coast";

  //     EmployeeEntity employeeEntity1 = EmployeeEntity
  //       .builder()
  //       .userName(userName1)
  //       .firstName(firstName1)
  //       .lastName(lastName1)
  //       .emailAddress(emailAddress1)
  //       .onlineStatus(onlineStatus1)
  //       .build();

  //     DepartmentEntity department1 = DepartmentEntity
  //       .builder()
  //       .firstName(departmentFirstName1)
  //       .lastName(departmentLastName1)
  //       .jobTitle(departmentJobTitle1)
  //       .employee(employeeEntity1)
  //       .hireDate(departmentHireDate1)
  //       .regionName(departmentRegionName1)
  //       .build();

  //     employeeEntity1.setDepartment(department1);
  //     departmentRepository.save(department1);
  //     employeeRepository.save(employeeEntity1);

  //     EmployeeEntity employeeEntity2 = EmployeeEntity
  //       .builder()
  //       .userName(userName2)
  //       .firstName(firstName2)
  //       .lastName(lastName2)
  //       .emailAddress(emailAddress2)
  //       .onlineStatus(onlineStatus2)
  //       .build();

  //     DepartmentEntity department2 = DepartmentEntity
  //       .builder()
  //       .firstName(departmentFirstName2)
  //       .lastName(departmentLastName2)
  //       .jobTitle(departmentJobTitle2)
  //       .employee(employeeEntity2)
  //       .hireDate(departmentHireDate2)
  //       .regionName(departmentRegionName2)
  //       .build();

  //     employeeEntity2.setDepartment(department2);
  //     employeeRepository.saveAndFlush(employeeEntity2);
  //     departmentRepository.saveAndFlush(department2);

  //     assertThat(departmentRepository.findAll()).isNotNull();

  //     departmentRepository.deleteAll();
  //     List<DepartmentEntity> emptyDepartment = departmentRepository.findAll();

  //     assertThat(emptyDepartment).isEmpty();
  //   }

}
