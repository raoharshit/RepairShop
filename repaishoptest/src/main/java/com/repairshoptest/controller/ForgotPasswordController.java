package com.repairshoptest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.repairshoptest.dto.ForgotPasswordRequest;
import com.repairshoptest.dto.ForgotPasswordResponse;
import com.repairshoptest.service.impl.ForgotPasswordService;
import com.repairshoptest.utils.JwtUtil;

@RestController
public class ForgotPasswordController {
	@Autowired
	private ForgotPasswordService service;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/forgotpassword")
	public ResponseEntity<?> generateOTP(@RequestParam("email") String email){
		System.out.println(email);
		ForgotPasswordResponse response = service.generateOTP(email);
    	return ResponseEntity.ok(response);
	}
	
	@PostMapping("/forgotpassword/resendotp")
	public ResponseEntity<?> resendOTP(@RequestParam("id") int id){
		ForgotPasswordResponse response = service.resendOTP(id);
    	return ResponseEntity.ok(response);
	}
	
	@PostMapping("/forgotpassword/verifyotp")
	public ResponseEntity<?> verifyOTP(@RequestParam("id") int id, @RequestParam("otp") String otp){
		String save = service.validateOTP(id, otp);
		String token = jwtUtil.generateToken(Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		System.out.println(token);
		headers.add("Set-cookie","jwt=" + token);
		return ResponseEntity.ok().headers(headers).body(save);
	}
	
	@PostMapping("/forgotpassword/changepassword")
	public ResponseEntity<?> verifyOTP(@Valid @RequestBody ForgotPasswordRequest request){
		int userId = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return ResponseEntity.ok(service.changePassword(userId,request));
	}
}