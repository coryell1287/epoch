package com.epoch.activitysystem.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.epoch.activitysystem.entity.DepartmentEntity;

@Repository
public interface DepartmentRepository
  extends JpaRepository<DepartmentEntity, UUID> {
  
}
