package com.epoch.activitysystem.entity;

import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import org.springframework.data.annotation.CreatedDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Accessors(chain = true)
@Entity
@Table(name = "DEPARTMENT")
public class DepartmentEntity {

  @Id
  @GeneratedValue
  @Column(name = "ID", nullable = false)
  private UUID id;

  @Version
  Long version;

  @Column(name = "FIRST_NAME")
  private String firstName;

  @Column(name = "LAST_NAME")
  private String lastName;

  @Column(name = "DEPARTMENT_NAME")
  private String departmentName;

  @CreatedDate
  @Column(name = "HIRE_DATE", nullable = false, updatable = false)
  private LocalDate hireDate;

  @Column(name = "JOB_TITLE")
  private String jobTitle;

  @Column(name = "REGION_NAME")
  private String regionName;

}
