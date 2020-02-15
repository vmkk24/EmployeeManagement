package com.employee.dto;

import lombok.Data;

@Data
public class EmployeeResponse {
	private Long employeeId;
	private String employeeName;
	private String designation;
}
