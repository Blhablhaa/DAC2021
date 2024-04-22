package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.LoginRequest;
import com.app.service.IBankClientService;

@RestController // end point provider for react app(postman)
@RequestMapping("/accounts")
public class BankClientController {
	//dependency : service layer i/f
	@Autowired
	private IBankClientService clntService;
	public BankClientController() {
		System.out.println("in ctor of " + getClass().getName());
	}
	//add REST API method for bank customer authentication
	@PostMapping("/signin")
	public ResponseEntity<?> autheticateCustomer(@RequestBody @Valid LoginRequest payload)
	{
		System.out.println("auth cust "+payload);
		//invoke service layer method for customer auth.
		return clntService.authenticateCustomer(payload);
	}
}
