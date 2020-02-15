package com.employee.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto extends ResponseDto{
	
	private Long employeeId;
	private String employeeName;

}
