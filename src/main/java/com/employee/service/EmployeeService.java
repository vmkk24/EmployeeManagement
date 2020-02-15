package com.employee.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.employee.dto.EmployeeRequestDto;
import com.employee.dto.ResponseDto;

public interface EmployeeService {
	ResponseDto registerRequest(@RequestBody EmployeeRequestDto employeeRequestDto);

	void approveAndRegister(EmployeeRequestDto employeeRequestDto);
}
