package com.epoch.activitysystem.repository;

import com.epoch.activitysystem.entity.EmployeeEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository
  extends JpaRepository<EmployeeEntity, UUID> {}
