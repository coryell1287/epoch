package com.epoch.activitysystem.service;

import com.epoch.activitysystem.entity.EmployeeEntity;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
  EmployeeEntity save(EmployeeEntity employee);

  EmployeeEntity findById(UUID id);

  List<EmployeeEntity> findAll();

  EmployeeEntity updateById(UUID id, EmployeeEntity employee);

  Map<String, String> deleteBy(UUID id);

  Map<String, String> deleteAll();

}
