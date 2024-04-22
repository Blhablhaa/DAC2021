package com.app.dto;




import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Customer {
	
	private String customerId;

	
	private String name;

	private String password;

}
