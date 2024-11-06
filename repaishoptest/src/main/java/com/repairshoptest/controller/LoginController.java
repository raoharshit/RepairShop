package com.repairshoptest.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.repairshoptest.dto.LoginRequest;
import com.repairshoptest.dto.LoginResponse;
import com.repairshoptest.service.LoginService;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
    		LoginResponse loginResponse = loginService.authenticateUser(loginRequest);
    		HttpHeaders headers = new HttpHeaders();
    		headers.add("Set-cookie","jwt=" + loginResponse.getToken());
        	return ResponseEntity.ok().headers(headers).body(loginResponse.getMessage());
    	
    	
    }
    
}