package com.app.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginRequest {
	@NotBlank(message = "cust id is required")
	private String customerId;
	@NotBlank(message = "pwd is required")
	private String password;
}
