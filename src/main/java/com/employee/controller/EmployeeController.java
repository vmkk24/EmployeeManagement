package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dto.EmployeeRequestDto;
import com.employee.dto.ResponseDto;
import com.employee.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employees")
@CrossOrigin
@Slf4j
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<ResponseDto> register(@RequestBody EmployeeRequestDto employeeRequestDto){
		log.info("Entering into register of EmployeeController");
		ResponseDto response=employeeService.registerRequest(employeeRequestDto);
		response.setMessage("Success");
		response.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(response,HttpStatus.OK);	
	}

}
