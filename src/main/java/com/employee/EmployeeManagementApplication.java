package com.employee;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class EmployeeManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
	}
	@Bean
	public Queue queue() {
		return new ActiveMQQueue("employee.queue");
	}
}
