package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.dto.LoginRequest;
import com.app.dto.LoginResponse;

@Service
public class BankClientServiceImpl implements IBankClientService {

	private RestTemplate template;
	//SpEL : Spring expression language
	@Value("${REST_CUSTOMER_AUTH}")
	private String authURL;

	// auto wire RestTemplateBuilder using cnstr based D.I
	@Autowired
	public BankClientServiceImpl(RestTemplateBuilder builder) {
		template = builder.build();
		System.out.println("in ctor " + template);
	}

	@Override
	public ResponseEntity<LoginResponse> authenticateCustomer(LoginRequest request) {
		System.out.println("url "+authURL);
		// Make REST call to NetBanking app : for cust auth.
		/*
		 * public <T> ResponseEntity<T> postForEntity(String url,
		 * 
		 * @Nullable Object request, Class<T> responseType)
		 * throws RestClientException
		 */
		return template.postForEntity
				(authURL, request,LoginResponse.class);
		
	}

}
