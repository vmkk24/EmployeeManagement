package com.employee.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.constants.ApplicationConstants;
import com.employee.dto.EmployeeRequestDto;
import com.employee.dto.EmployeeResponse;
import com.employee.dto.LoginRequestDto;
import com.employee.dto.LoginResponseDto;
import com.employee.dto.ResponseDto;
import com.employee.entity.Employee;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.messagingQueue.Publisher;
import com.employee.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	Publisher publisher;

	@Autowired
	EmployeeRepository employeeRepository;

	/**
	 * 
	 * @author PriyaDharshini S.
	 * @since 2020-02-11. This method will authenticate the employee.
	 * @param loginDto - details of the employee login
	 * @return LoginResponseDto which has status message,statusCode and employee
	 *         details
	 * @throws EmployeeNotFoundException it will throw the exception if the employee
	 *                                   is not there.
	 * 
	 */
	@Override
	public LoginResponseDto authenticateEmployee(LoginRequestDto loginRequestDto) throws EmployeeNotFoundException {
		Optional<Employee> employee = employeeRepository.findByEmployeeIdAndPassword(loginRequestDto.getEmployeeId(),
				loginRequestDto.getPassword());
		if (!employee.isPresent()) {
			log.error("Exception occurred in EmployeeServiceImpl authenticateEmployee method:"
					+ ApplicationConstants.EMPLOYEE_NOT_FOUND);
			throw new EmployeeNotFoundException(ApplicationConstants.EMPLOYEE_NOT_FOUND);
		}
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		loginResponseDto.setEmployeeId(employee.get().getEmployeeId());
		loginResponseDto.setEmployeeName(employee.get().getEmployeeName());
		log.info("Entering into EmployeeServiceImpl authenticateEmployee method: Authentication Successful");
		return loginResponseDto;

	}

	public ResponseDto registerRequest(EmployeeRequestDto employeeRequestDto) {
		log.info("Entering into register() of EmployeeServiceImpl");
		publisher.sendTopic(employeeRequestDto);
		return new ResponseDto();
	}

	public void approveAndRegister(EmployeeRequestDto employeeRequestDto) {
		log.info("Entering into approveAndRegister() of EmployeeServiceImpl");
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeRequestDto, employee);
		Random random = new Random();
		employee.setPassword("" + random.nextInt(9999));
		if (employeeRequestDto.getExperience() <= ApplicationConstants.MINIMUM_EXPERIENCE) {
			employee.setDesignation(ApplicationConstants.EMPLOYEE);
		} else {
			employee.setDesignation(ApplicationConstants.MANAGER);
		}
		employeeRepository.save(employee);
		log.debug("Saved successfully in approveAndRegister");
		EmployeeResponse employeeResponse = new EmployeeResponse();
		publisher.sendEmployeeTopic(employeeResponse);
		log.debug("Saved successfully in sendEmployeeTopic:" + employeeResponse);
	}

}
