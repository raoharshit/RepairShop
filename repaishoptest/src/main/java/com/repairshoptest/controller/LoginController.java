package com.repairshoptest.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.repairshoptest.dto.LoginRequest;
import com.repairshoptest.dto.LoginResponse;
import com.repairshoptest.service.LoginService;
import com.repairshoptest.utils.JwtUtil;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        // Authenticate the user (this is just a placeholder for actual authentication logic)
    	System.out.println("in login");
    	LoginResponse loginResponse = loginService.authenticateUser(loginRequest);
    	if(loginResponse == null) {
    		//throw new Exception("Some error occurred");
    		return null;
    	}
    	return ResponseEntity.ok(loginResponse);
    }
    
    

    // LoginRequest class (for request body)
    
    
}