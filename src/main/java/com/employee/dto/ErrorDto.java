package com.employee.dto;

import lombok.Data;

@Data
public class ErrorDto {
	
	private String message;
	private Integer statusCode;

}
