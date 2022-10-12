package com.epoch.activitysystem.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.epoch.activitysystem.entity.EmployeeEntity;

@Repository
public interface EmployeeRepository
  extends JpaRepository<EmployeeEntity, UUID> {
}
