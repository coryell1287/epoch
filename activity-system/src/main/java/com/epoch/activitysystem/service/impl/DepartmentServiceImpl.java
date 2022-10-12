package com.epoch.activitysystem.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.epoch.activitysystem.entity.DepartmentEntity;
import com.epoch.activitysystem.exceptions.EmployeeNotFoundException;
import com.epoch.activitysystem.repository.DepartmentRepository;
import com.epoch.activitysystem.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

  private static final String MESSAGE = "message";

  @Autowired
  DepartmentRepository repository;

  @Override
  public List<DepartmentEntity> findAll() {
    return repository.findAll();
  }

  @Override
  public DepartmentEntity findById(UUID id) {
    return repository.findById(id).orElseThrow(EmployeeNotFoundException::new);
  }

  @Override
  public DepartmentEntity updateById(
    UUID id,
    DepartmentEntity updatedDeptDetails
  ) {
    return repository
      .findById(id)
      .map(
        currentDeptDetails ->
          repository.save(updateEntity(currentDeptDetails, updatedDeptDetails))
      )
      .orElseThrow(EmployeeNotFoundException::new);
  }

  @Override
  public Map<String, String> deleteById(UUID id) {
    return repository
      .findById(id)
      .map(deptOpt -> deleteEmployeeFromDepartment(id))
      .orElseThrow(EmployeeNotFoundException::new);
  }

  @Override
  public Map<String, String> deleteAll() {
    return deleteEmployeeFromDepartment(null);
  }

  private Map<String, String> deleteEmployeeFromDepartment(UUID id) {
    Map<String, String> deleteMessage = new HashMap<>();

    if (id == null) {
      List<DepartmentEntity> departmentList = repository.findAll();

      if (departmentList.isEmpty()) {
        deleteMessage.put(MESSAGE, "Department is empty.");
        return deleteMessage;
      }
      repository.deleteAll();
      deleteMessage.put(MESSAGE, "All employees deleted successfully.");
      return deleteMessage;
    }
    repository.deleteById(id);
    deleteMessage.put(MESSAGE, "Employee deleted successfully.");
    return deleteMessage;
  }

  private DepartmentEntity updateEntity(
    DepartmentEntity currentEntityDetails,
    DepartmentEntity updatedEntityDetails
  ) {
    return currentEntityDetails
      .setDepartmentName(updatedEntityDetails.getDepartmentName())
      .setRegionName(updatedEntityDetails.getRegionName())
      .setJobTitle(currentEntityDetails.getJobTitle());
  }
}
