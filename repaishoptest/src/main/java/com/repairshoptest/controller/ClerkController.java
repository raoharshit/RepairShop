package com.repairshoptest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.repairshoptest.dto.ClerkRequestDTO;
import com.repairshoptest.dto.ClerkResponseDTO;
import com.repairshoptest.dto.CustomerRequestDTO;
import com.repairshoptest.dto.CustomerResponseDTO;
import com.repairshoptest.dto.PasswordChangeRequest;
import com.repairshoptest.dto.PasswordChangeResponse;
import com.repairshoptest.dto.RepairPersonResponseDTO;
import com.repairshoptest.dto.RepairServiceResponseDTO;
import com.repairshoptest.model.Clerk;
import com.repairshoptest.model.Customer;
import com.repairshoptest.model.RepairPerson;
import com.repairshoptest.model.RepairService;
import com.repairshoptest.service.ClerkService;
import com.repairshoptest.service.CustomerService;
import com.repairshoptest.service.RepairPersonService;
import com.repairshoptest.service.RepairServiceService;

@RestController
@RequestMapping("/clerk")
public class ClerkController {
	
	@Autowired
	ClerkService clerkService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	RepairPersonService repairPersonService;
	
	@Autowired
	RepairServiceService repairServiceService;
	
	@GetMapping("/profile")
	public ResponseEntity<?> getProfile(){
		System.out.println("in clerk controller");
		int clerkId = Integer.parseInt((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		Clerk clerk = clerkService.findById(clerkId);
		if(clerk == null) {
			//throw new Exception("User not found");
			return null;
		}
		
		return ResponseEntity.ok(ClerkResponseDTO.fromEntity(clerk));
		
	}
	
	@PutMapping("/profile")
	public ResponseEntity<?> updateProfile(@RequestBody ClerkRequestDTO clerkRequestDTO) {
		int clerkId = Integer.parseInt((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		Clerk clerk = clerkService.update(clerkId, clerkRequestDTO);
		if(clerk == null) {
			//throw new Exception("Some error occurred");
			return null;
		}
		return ResponseEntity.ok(ClerkResponseDTO.fromEntity(clerk));
	}
	
	
	@PutMapping("/password")
	public ResponseEntity<?> updatePassword(@RequestBody PasswordChangeRequest passwordChangeRequest) {
		int clerkId = Integer.parseInt((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		PasswordChangeResponse passwordChangeResponse = repairPersonService.updatePassword(clerkId, passwordChangeRequest);
		if(passwordChangeResponse == null) {
			//throw new Exception("Some error occurred");
			return null;
		}
		return ResponseEntity.ok(passwordChangeResponse);
	}
	
	@GetMapping("/customer")
	public ResponseEntity<?> findCustomers(@RequestParam(value="search", defaultValue="") String search,@RequestParam(value="onlyMine", defaultValue="false") boolean onlyMine, @RequestParam(value="page",defaultValue="0") int page, @RequestParam(value="limit",defaultValue="10")int limit) {
		int clerkId = Integer.parseInt((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		Page<Customer> customers = customerService.getCustomers(search, onlyMine, clerkId, page, limit);
		return ResponseEntity.ok(customers.map(CustomerResponseDTO::fromEntity));
	}
	
	@GetMapping("/service")
	public ResponseEntity<?> getServices(@RequestParam(value = "search", defaultValue = "") String search,@RequestParam(value="onlyMine", defaultValue="false") boolean onlyMine, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value="limit",defaultValue="10")int limit, @RequestParam(value = "custId", defaultValue = "") Integer custId, @RequestParam(value = "repairId", defaultValue = "") Integer repairId) {
		int clerkId = Integer.parseInt((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		Page<RepairService> servicePage;
		if(custId != null) {
			servicePage = repairServiceService.getServicesForRole("Customer", custId, false, search, page, limit);
		}else if(repairId != null) {
			servicePage = repairServiceService.getServicesForRole("Customer", repairId, false, search, page, limit);
		}else {
			servicePage = repairServiceService.getServicesForRole("Clerk", clerkId, onlyMine, search, page, limit);
		}
		
		
		return ResponseEntity.ok(servicePage.map(RepairServiceResponseDTO::fromEntity));
	}
	
	@GetMapping("/repairperson")
	public ResponseEntity<?> getRepairPersons(@RequestParam(value = "search", defaultValue = "") String search, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value="limit",defaultValue="10")int limit,@RequestParam(value="category",defaultValue="") String specialty ) {
		Page<RepairPerson> repairPersonPage = repairPersonService.findBySearch(search, page, limit, specialty);
		
		return ResponseEntity.ok(repairPersonPage.map(RepairPersonResponseDTO::fromEntity));
	}
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<?> findCustomerById(@PathVariable("id") int id) {
		Customer customer = customerService.findById(id);
		if(customer == null) {
			// throw new Exception("Customer not found");
			return null;
		}
		return ResponseEntity.ok(CustomerResponseDTO.fromEntity(customer));
	}
	
	
	@GetMapping("/repairperson/{id}")
	public ResponseEntity<?> getProfile(@PathVariable("id") int repairId){
		RepairPerson repairPerson = repairPersonService.findById(repairId);
		if(repairPerson == null) {
			//throw new Exception("User not found");
			return null;
		}
		
		return ResponseEntity.ok(RepairPersonResponseDTO.fromEntity(repairPerson));
		
	}
	
	@PostMapping("/customer")
	public ResponseEntity<?> addCustomer(@RequestBody CustomerRequestDTO dto){
		int clerkId = Integer.parseInt((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		Customer customer = customerService.add(clerkId, dto);
		if(customer == null) {
			//throw new Exception("Some error occurred");
			return null;
		}
		return ResponseEntity.ok("Customer added successfully");
	}
	
	
//	@GetMapping("/byid")
//	public Clerk findClerkById(@RequestHeader(value = "clerkId") int clerkId) {
//		Clerk clerk = clerkService.findById(clerkId);
//		return clerk;
//	}
//	
//	@GetMapping("/byemail")
//	public Clerk findClerkByEmail(@RequestParam(value = "email") String email) {
//		Clerk clerk = clerkService.findByEmail(email);
//		return clerk;
//	}
//	
//	@PostMapping("/addClerk")
//	public Clerk addClerk(@RequestBody ClerkRequestDTO clerkRequestDTO) {
//		Clerk clerk = clerkService.add(clerkRequestDTO);
//		return clerk;
//	}
//	
//	@PostMapping("/updateClerk")
//	public Clerk updateClerk(@RequestHeader("clerkId") int clerkId, @RequestBody ClerkRequestDTO clerkRequestDTO) {
//		Clerk clerk = clerkService.update(clerkId,clerkRequestDTO);
//		return clerk;
//	}
	
	
	
	
}
