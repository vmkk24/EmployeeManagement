package com.employee.messagingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.employee.constants.ApplicationConstants;
import com.employee.dto.EmployeeRequestDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Publisher {
	@Autowired
	private JmsTemplate jmsTemplate;
	
	public void sendTopic(EmployeeRequestDto employeeRequestDto) {
        log.info("sending with convertAndSend() to topic <" + employeeRequestDto + ">");
        jmsTemplate.convertAndSend(ApplicationConstants.ORDER_TOPIC, employeeRequestDto);
    }
}
