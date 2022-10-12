package com.epoch.activitysystem.service.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.epoch.activitysystem.entity.DepartmentEntity;
import com.epoch.activitysystem.entity.EmployeeEntity;
import com.epoch.activitysystem.exceptions.EmployeeNotFoundException;
import com.epoch.activitysystem.repository.DepartmentRepository;
import com.epoch.activitysystem.repository.EmployeeRepository;
import com.epoch.activitysystem.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  EmployeeRepository repository;

  @Autowired
  DepartmentRepository departmentRepo;

  @Override
  @Transactional
  public EmployeeEntity save(EmployeeEntity employee) {
    log.info("Calling save employee service.");
    return repository.save(toEntity(employee));
  }

  @Override
  @Transactional(readOnly = true)
  public EmployeeEntity findById(UUID id) {
    return repository.findById(id).orElseThrow(EmployeeNotFoundException::new);
  }

  @Override
  public List<EmployeeEntity> findAll() {
    return repository.findAll();
  }

  @Override
  public EmployeeEntity updateById(UUID id, EmployeeEntity employee) {
    return repository
      .findById(id)
      .map(
        employeeOpt -> repository.save(updateEmployee(employeeOpt, employee))
      )
      .orElseThrow(EmployeeNotFoundException::new);
  }

  @Override
  public Map<String, String> deleteBy(UUID id) {
    return repository
      .findById(id)
      .map(employee -> deleteEmployee(id))
      .orElseThrow(EmployeeNotFoundException::new);
  }

  @Override
  public Map<String, String> deleteAll() {
    repository.deleteAll();
    Map<String, String> message = new HashMap<>();
    message.put("message", "All employees deleted successfully.");

    return message;
  }

  private EmployeeEntity toEntity(EmployeeEntity entity) {
    DepartmentEntity department = DepartmentEntity
      .builder()
      .firstName(entity.getFirstName())
      .lastName(entity.getLastName())
      .departmentName(entity.getDepartment().getDepartmentName())
      .hireDate(LocalDate.now())
      .jobTitle(entity.getDepartment().getJobTitle())
      .regionName(entity.getDepartment().getRegionName())
      .build();

    DepartmentEntity savedDepartment = departmentRepo.save(department);

    return EmployeeEntity
      .builder()
      .firstName(entity.getFirstName())
      .lastName(entity.getLastName())
      .userName(entity.getUserName())
      .department(savedDepartment)
      .emailAddress(entity.getEmailAddress())
      .onlineStatus(false)
      .build();
  }

  private Map<String, String> deleteEmployee(UUID id) {
    Map<String, String> message = new HashMap<>();
    repository.deleteById(id);
    message.put("message", "Employee deleted successfully.");
    return message;
  }

  private EmployeeEntity updateEmployee(
    EmployeeEntity employee,
    EmployeeEntity updatedEmployee
  ) {
    return employee
      .setDepartment(
        updatedEmployee.getDepartment() != null
          ? updatedEmployee.getDepartment()
          : employee.getDepartment()
      )
      .setEmailAddress(updatedEmployee.getEmailAddress())
      .setUserName(updatedEmployee.getUserName())
      .setOnlineStatus(updatedEmployee.getOnlineStatus());
  }
}
