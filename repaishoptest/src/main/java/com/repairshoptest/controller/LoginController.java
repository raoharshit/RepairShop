package com.repairshoptest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.repairshop.exception.InvalidCredentialsException;
import com.repairshop.exception.ResourceNotFoundException;
import com.repairshoptest.dto.LoginRequest;
import com.repairshoptest.dto.LoginResponse;
import com.repairshoptest.service.LoginService;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        // Authenticate the user (this is just a placeholder for actual authentication logic)
    	try {
    		LoginResponse loginResponse = loginService.authenticateUser(loginRequest);
    		HttpHeaders headers = new HttpHeaders();
    		headers.add("Set-cookie","jwt=" + loginResponse.getToken());
        	return ResponseEntity.ok().headers(headers).body(loginResponse.getMessage());
    	}catch(ResourceNotFoundException ex) {
    		 return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    	}catch(InvalidCredentialsException ex) {
    		return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    	}
    	
    }
    
    

    // LoginRequest class (for request body)
    
    
}