package com.epoch.activitysystem.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Builder
@Entity
@Table(
  name = "EMPLOYEE",
  uniqueConstraints = {
    @UniqueConstraint(columnNames = { "EMAIL_ADDRESS", "USER_NAME" }),
  }
)
public class EmployeeEntity {

  @Id
  @GeneratedValue
  @Column(name = "ID")
  private UUID id;

  @Column(name = "FIRST_NAME")
  private String firstName;

  @Column(name = "LAST_NAME")
  private String lastName;

  @Column(name = "USER_NAME", unique = true)
  private String userName;

  @Email
  @Column(name = "EMAIL_ADDRESS", unique = true)
  private String emailAddress;

  @GeneratedValue
  @Column(name = "DEPARTMENT_ID")
  private UUID departmentID;

  @Column(name = "DEPARTMENT")
  private String department;

  @Column(name = "ROLE")
  private String role;

  @Column(name = "ONLINE_STATUS")
  private Boolean onlineStatus;
}
