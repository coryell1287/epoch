package com.epoch.activitysystem.service;

import com.epoch.activitysystem.entity.EmployeeEntity;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
  public EmployeeEntity save(EmployeeEntity employee);

  public EmployeeEntity findById(UUID id);

  public List<EmployeeEntity> findAll();

  public EmployeeEntity updateById(UUID id, EmployeeEntity employee);

  public Map<String, String> deleteBy(UUID id);

  public Map<String, String> deleteAll();
}
