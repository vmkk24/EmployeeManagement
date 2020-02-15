package com.employee.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class EmployeeRequestDto {
	
	private String employeeName;
	private Date dateOfBirth;
	private String gender;
	private String email;
	private Integer experience;
	private Date dateOfJoining;
}
