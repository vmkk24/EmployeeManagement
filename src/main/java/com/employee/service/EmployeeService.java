package com.employee.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.employee.dto.EmployeeRequestDto;
import com.employee.dto.LoginRequestDto;
import com.employee.dto.LoginResponseDto;
import com.employee.dto.ResponseDto;
import com.employee.exception.EmployeeNotFoundException;

public interface EmployeeService {
	ResponseDto registerRequest(@RequestBody EmployeeRequestDto employeeRequestDto);

	void approveAndRegister(EmployeeRequestDto employeeRequestDto);
	
	LoginResponseDto authenticateEmployee(LoginRequestDto loginRequestDto) throws EmployeeNotFoundException;
}
