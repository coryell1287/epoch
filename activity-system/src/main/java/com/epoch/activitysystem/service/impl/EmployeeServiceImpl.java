package com.epoch.activitysystem.service.impl;

import com.epoch.activitysystem.entity.EmployeeEntity;
import com.epoch.activitysystem.exceptions.EmployeeNotFoundException;
import com.epoch.activitysystem.repository.EmployeeRepository;
import com.epoch.activitysystem.service.EmployeeService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  EmployeeRepository repository;

  @Override
  public EmployeeEntity save(EmployeeEntity employee) {
    log.info("Calling save employee service.");
    return repository.save(toEntity(employee));
  }

  @Override
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
    return EmployeeEntity
      .builder()
      .firstName(entity.getFirstName())
      .lastName(entity.getLastName())
      .userName(entity.getUserName())
      .departmentID(UUID.randomUUID())
      .department(entity.getDepartment())
      .emailAddress(entity.getEmailAddress())
      .role(entity.getRole())
      .onlineStatus(entity.getOnlineStatus())
      .build();
  }

  private Map<String, String> deleteEmployee(UUID id) {
    Map<String, String> message = new HashMap<>();
    System.out.println("================================" + "Delete Employee");
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
      .setOnlineStatus(updatedEmployee.getOnlineStatus())
      .setRole(updatedEmployee.getRole());
  }
}
