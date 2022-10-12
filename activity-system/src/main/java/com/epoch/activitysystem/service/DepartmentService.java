package com.epoch.activitysystem.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.epoch.activitysystem.entity.DepartmentEntity;

public interface DepartmentService {

  List<DepartmentEntity> findAll();

  DepartmentEntity findById(UUID id);

  DepartmentEntity updateById(UUID id, DepartmentEntity department);

  Map<String, String> deleteById(UUID id);

  Map<String, String> deleteAll();
 
}
