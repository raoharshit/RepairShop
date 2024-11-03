package com.repairshoptest.controller;

import java.util.List;

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

import com.repairshoptest.dto.AdditionalItemRFAResponseDTO;
import com.repairshoptest.dto.CustomerRequestDTO;
import com.repairshoptest.dto.CustomerResponseDTO;
import com.repairshoptest.dto.PasswordChangeRequest;
import com.repairshoptest.dto.PasswordChangeResponse;
import com.repairshoptest.dto.RepairServiceResponseDTO;
import com.repairshoptest.model.AdditionalItemRFA;
import com.repairshoptest.model.Customer;
import com.repairshoptest.model.RepairService;
import com.repairshoptest.model.ServiceStatus;
import com.repairshoptest.service.CustomerService;
import com.repairshoptest.service.RFAService;
import com.repairshoptest.service.RepairServiceService;
import com.repairshoptest.service.ServiceStatusService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
//	int custId = Integer.parseInt((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	RepairServiceService repairServiceService;
	
	@Autowired
	RFAService rfaService;
	
	@Autowired
	ServiceStatusService serviceStatusService;
	
	
	
	@GetMapping("/profile")
	public ResponseEntity<?> getProfile(){
		int custId = Integer.parseInt((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		Customer customer = customerService.findById(custId);
		if(customer == null) {
			//throw new Exception("User not found");
			return null;
		}
		
		return ResponseEntity.ok(CustomerResponseDTO.fromEntity(customer));
		
	}
	
	@PutMapping("/profile")
	public ResponseEntity<?> updateProfile(@RequestBody CustomerRequestDTO customerRequestDTO) {
		int custId = Integer.parseInt((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		Customer update = customerService.update(custId, customerRequestDTO);
		if(update == null) {
			//throw new Exception("Some error occurred");
			return null;
		}
		return ResponseEntity.ok(CustomerResponseDTO.fromEntity(update));
	}
	
	@PutMapping("/password")
	public ResponseEntity<?> updatePassword(@RequestBody PasswordChangeRequest passwordChangeRequest) {
		int custId = Integer.parseInt((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		PasswordChangeResponse passwordChangeResponse = customerService.updatePassword(custId, passwordChangeRequest);
		if(passwordChangeResponse == null) {
			//throw new Exception("Some error occurred");
			return null;
		}
		return ResponseEntity.ok(passwordChangeResponse);
	}
	
	@GetMapping("/service")
	public ResponseEntity<?> getServices(@RequestParam(value = "search", defaultValue = "") String search, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value="limit",defaultValue="10")int limit) {
		int custId = Integer.parseInt((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		Page<RepairService> servicePage = repairServiceService.getServicesForRole("Customer", custId, false, search, page, limit);
		
		return ResponseEntity.ok(servicePage.map(RepairServiceResponseDTO::fromEntity));
	}
	
	@GetMapping("/request")
	public ResponseEntity<?> getRequests(@RequestParam(value = "search", defaultValue = "") String search, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value="limit",defaultValue="10")int limit) {
		int custId = Integer.parseInt((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		Page<AdditionalItemRFA> rfaPage = rfaService.findRFAForRole("Customer", custId, search, page, limit);
		
		return ResponseEntity.ok(rfaPage.map(AdditionalItemRFAResponseDTO::fromEntity));
	}
	
	@GetMapping("/request/{id}")
	public ResponseEntity<?> getRequestById(@PathVariable("id") int id) {
		AdditionalItemRFA rfa = rfaService.findById(id);
		
		if(rfa == null) {
			//throw new Exception("RFA not found");
			return null;
		}
		
		return ResponseEntity.ok(AdditionalItemRFAResponseDTO.fromEntity(rfa));
	}
	
	@GetMapping("/service/{id}")
	public ResponseEntity<?> getServiceById(@PathVariable("id") int id) {
		RepairService repairService = repairServiceService.findById(id);
		
		if(repairService == null) {
			//throw new Exception("Service not found");
			return null;
		}
		
		return ResponseEntity.ok(RepairServiceResponseDTO.fromEntity(repairService));
	}
	
	@GetMapping("/service/{id}/request")
	public ResponseEntity<?> getApprovalsById(@PathVariable("id") int id) {
		List<AdditionalItemRFA> list = rfaService.findByRepairServiceId(id);
		return ResponseEntity.ok(list.stream().map(AdditionalItemRFAResponseDTO::fromEntity).toList());
	}
	
	@GetMapping("/service/{id}/history")
	public ResponseEntity<?> getHistoryById(@PathVariable("id") int id) {
		List<ServiceStatus> list = serviceStatusService.findByRepairServiceId(id);
		
		return ResponseEntity.ok(list);
	}
	
	@PutMapping("/request/{id}")
	public ResponseEntity<?> updateRequest(@PathVariable("id") int id, @RequestParam("response") String response){
		boolean updateRFA = rfaService.updateRFA(id, response);
		if(updateRFA) {
			return ResponseEntity.ok("Request " + response);
		}
		//throw new Exception("Some error occurred");
		return null;
	}
	
//	@GetMapping("/customer/profile/byemail")
//	public Customer findCustomerByEmail(@RequestParam("email") String email) {
//		Customer customer = customerService.findByEmail(email);
//		return customer;
//	}
	
//	@PutMapping("/clerk/customers")
//	public Page<Customer> findCustomers(@RequestParam(value="search", defaultValue="") String search,@RequestParam(value="onlyMine", defaultValue="false") boolean onlyMine, @RequestParam(value="page",defaultValue="0") int page, @RequestParam(value="limit",defaultValue="10") int limit,@RequestHeader("clerkId") int clerkId) {
//		Page<Customer> customers = customerService.getCustomers(search, onlyMine, clerkId, page, limit);
//		return customers;
//	}
//	
//	@GetMapping("/clerk/allcustomers")
//	public List<Customer> findAllCustomers() {
//		List<Customer> customers = customerService.findAll();
//		return customers;
//	}
//	
//	@PostMapping("/clerk/createcustomer")
//	public Customer createCustomer(@RequestBody CustomerRequestDTO customerRequestDTO, @RequestHeader("clerkId") int clerkId) {
//		Customer customer = customerService.add(clerkId, customerRequestDTO );
//		return customer;
//	}

}
