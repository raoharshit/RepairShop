package com.repairshoptest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.repairshoptest.dto.ClerkDTO;
import com.repairshoptest.model.Clerk;
import com.repairshoptest.service.ClerkService;

@RestController
@RequestMapping("/clerk")
public class ClerkController {
	
	@Autowired
	ClerkService clerkService;
	
	
	
	
	@GetMapping("/byid")
	public Clerk findClerkById(@RequestHeader(value = "clerkId") int clerkId) {
		Clerk clerk = clerkService.findById(clerkId);
		return clerk;
	}
	
	@GetMapping("/byemail")
	public Clerk findClerkByEmail(@RequestParam(value = "email") String email) {
		Clerk clerk = clerkService.findByEmail(email);
		return clerk;
	}
	
	@PostMapping("/addClerk")
	public Clerk addClerk(@RequestBody ClerkDTO clerkDTO) {
		Clerk clerk = clerkService.add(clerkDTO);
		return clerk;
	}
	
	@PostMapping("/updateClerk")
	public Clerk updateClerk(@RequestHeader("clerkId") int clerkId, @RequestBody ClerkDTO clerkDTO) {
		Clerk clerk = clerkService.update(clerkId,clerkDTO);
		return clerk;
	}
	
	
	
	
}
