package com.employee.messagingQueue;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	@JmsListener(destination = "employee.queue")
	public void receiveQueue(String text) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Message Received: "+text);
	}
}
