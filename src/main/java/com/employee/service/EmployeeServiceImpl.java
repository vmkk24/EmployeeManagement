package com.employee.service;

import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.constants.ApplicationConstants;
import com.employee.dto.EmployeeRequestDto;
import com.employee.dto.ResponseDto;
import com.employee.entity.Employee;
import com.employee.messagingQueue.Publisher;
import com.employee.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	Publisher publisher;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public ResponseDto registerRequest(EmployeeRequestDto employeeRequestDto) {
		log.info("Entering into register() of EmployeeServiceImpl");
		publisher.sendTopic(employeeRequestDto);
		return new ResponseDto();
	}
	
	public void approveAndRegister(EmployeeRequestDto employeeRequestDto) {
		log.info("Entering into approveAndRegister() of EmployeeServiceImpl");
		Employee employee= new Employee();
		BeanUtils.copyProperties(employeeRequestDto, employee);
		Random random = new Random();
		employee.setPassword(""+random.nextInt(9999));
		if(employeeRequestDto.getExperience() <= ApplicationConstants.MINIMUM_EXPERIENCE) {
		employee.setDesignation(ApplicationConstants.EMPLOYEE);
		}else {
			employee.setDesignation(ApplicationConstants.MANAGER);
		}
		employeeRepository.save(employee);
		log.debug("Saved successfully in approveAndRegister");
	}
}
