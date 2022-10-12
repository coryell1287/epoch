package com.epoch.activitysystem.entity;

import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
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
  @Column(name = "ID", nullable = false)
  private UUID id;

  @Column(name = "FIRST_NAME")
  private String firstName;

  @Column(name = "LAST_NAME")
  private String lastName;

  @NotBlank
  @Column(name = "USER_NAME", unique = true)
  private String userName;

  @Email
  @Column(name = "EMAIL_ADDRESS", unique = true)
  private String emailAddress;

  @Column(name = "ONLINE_STATUS", nullable = false, columnDefinition = "boolean default false")
  private Boolean onlineStatus;
  
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "DEPARTMENT_ID")
  private DepartmentEntity department;
}
