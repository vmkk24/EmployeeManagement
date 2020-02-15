package com.employee.messagingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.employee.constants.ApplicationConstants;
import com.employee.dto.EmployeeRequestDto;
import com.employee.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Subscriber {
	@Autowired
	EmployeeService employeeService;
	
	@JmsListener(destination = ApplicationConstants.ORDER_TOPIC, containerFactory = "topicListenerFactory")
	public void receiveTopicMessage(EmployeeRequestDto employeeRequestDto) {
		System.out.println("received <" + employeeRequestDto + ">");
		employeeService.approveAndRegister(employeeRequestDto);
		log.info("received <" + employeeRequestDto + ">");
	}
}
