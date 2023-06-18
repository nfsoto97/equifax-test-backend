package com.equifax.demo.login.dto;


import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthRequest {
	@NotNull @Email @Length(min = 5, max = 50)
	private String email;
	
	@NotNull @Length(min = 5, max = 10)
	private String password;
	
}
