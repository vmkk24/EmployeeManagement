package com.employee.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "employeeId", initialValue = 5000, allocationSize = 1)
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeId")
	private Long employeeId;
	private String employeeName;
	private Date dateOfBirth;
	private String gender;
	@Column(unique=true)
	private String email;
	private Integer experience;
	private Date dateOfJoining;
	private String designation;
	private String password;
}
