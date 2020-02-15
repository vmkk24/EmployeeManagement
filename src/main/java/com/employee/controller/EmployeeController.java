package com.employee.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.constants.ApplicationConstants;
import com.employee.dto.EmployeeRequestDto;
import com.employee.dto.LoginRequestDto;
import com.employee.dto.LoginResponseDto;
import com.employee.dto.ResponseDto;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employees")
@CrossOrigin
@Slf4j
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	/**
	 * 
	 * @author PriyaDharshini S.
	 * @since 2020-02-11. This method will authenticate the employee.
	 * @param loginDto - details of the employee login
	 * @return LoginResponseDto which has status message,statusCode and employee details
	 * @throws EmployeeNotFoundException it will throw the exception if the employee is
	 *                                 not there.
	 * 
	 */
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> authenticateEmployee(@Valid @RequestBody LoginRequestDto loginRequestDto)
			throws EmployeeNotFoundException {
		LoginResponseDto loginResponseDto = employeeService.authenticateEmployee(loginRequestDto);
		log.info("Entering into EmployeeController authenticateEmployee method: calling employeeService");
		loginResponseDto.setMessage(ApplicationConstants.AUTHENTICATION_SUCCESSFUL);
		loginResponseDto.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ResponseDto> register(@RequestBody EmployeeRequestDto employeeRequestDto){
		log.info("Entering into register of EmployeeController");
		ResponseDto response=employeeService.registerRequest(employeeRequestDto);
		response.setMessage("Success");
		response.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(response,HttpStatus.OK);	
	}

}
