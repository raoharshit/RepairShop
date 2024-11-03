package com.repairshoptest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.repairshoptest.dto.RegisterRequest;
import com.repairshoptest.dto.RegisterResponse;
import com.repairshoptest.service.RegisterService;

@RestController
public class RegisterController {
	
	@Autowired
	private RegisterService registerService;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
		RegisterResponse registerResponse = registerService.registerUser(registerRequest);
		
		if(registerResponse == null) {
			//throw new Exception("Some error occurred");
			return null;
		}
		
		return ResponseEntity.ok(registerResponse);
	}

}
