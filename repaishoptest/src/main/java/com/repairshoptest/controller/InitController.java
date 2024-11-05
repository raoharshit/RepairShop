package com.repairshoptest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.repairshoptest.dto.UserResponseDTO;
import com.repairshoptest.model.User;
import com.repairshoptest.service.UserService;

@RestController
public class InitController {

	@Autowired
	UserService userService;

	@GetMapping("/init")
	public ResponseEntity<?> init() {
		int userId = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		User user = userService.findById(userId);
		return ResponseEntity.ok(UserResponseDTO.fromEntity(user));

	}

}
