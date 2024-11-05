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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.repairshoptest.dto.AdditionalItemRFAResponseDTO;
import com.repairshoptest.dto.ClerkRequestDTO;
import com.repairshoptest.dto.ClerkResponseDTO;
import com.repairshoptest.dto.CustomerRequestDTO;
import com.repairshoptest.dto.CustomerResponseDTO;
import com.repairshoptest.dto.InvoiceGenerateResponse;
import com.repairshoptest.dto.InvoiceResponse;
import com.repairshoptest.dto.PasswordChangeRequest;
import com.repairshoptest.dto.PasswordChangeResponse;
import com.repairshoptest.dto.RepairPersonResponseDTO;
import com.repairshoptest.dto.RepairServiceRequestDTO;
import com.repairshoptest.dto.RepairServiceResponseDTO;
import com.repairshoptest.model.AdditionalItemRFA;
import com.repairshoptest.model.Clerk;
import com.repairshoptest.model.Customer;
import com.repairshoptest.model.RepairPerson;
import com.repairshoptest.model.RepairService;
import com.repairshoptest.model.ServiceStatus;
import com.repairshoptest.service.ClerkService;
import com.repairshoptest.service.CustomerService;
import com.repairshoptest.service.InvoiceService;
import com.repairshoptest.service.RFAService;
import com.repairshoptest.service.RepairPersonService;
import com.repairshoptest.service.RepairServiceService;
import com.repairshoptest.service.ServiceStatusService;

@RestController
@RequestMapping("/clerk")
public class ClerkController {

	@Autowired
	private ClerkService clerkService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private RepairPersonService repairPersonService;

	@Autowired
	private RepairServiceService repairServiceService;

	@Autowired
	private RFAService rfaService;

	@Autowired
	private ServiceStatusService serviceStatusService;
	
	@Autowired
	private InvoiceService invoiceService;

	@GetMapping("/profile")
	public ResponseEntity<?> getProfile() {

		int clerkId = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		Clerk clerk = clerkService.findById(clerkId);
		return ResponseEntity.ok(ClerkResponseDTO.fromEntity(clerk));

	}

	@PutMapping("/profile")
	public ResponseEntity<?> updateProfile(@RequestBody ClerkRequestDTO clerkRequestDTO) {

		int clerkId = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

		Clerk clerk = clerkService.update(clerkId, clerkRequestDTO);
		return ResponseEntity.ok(ClerkResponseDTO.fromEntity(clerk));

	}

	@PutMapping("/password")
	public ResponseEntity<?> updatePassword(@RequestBody PasswordChangeRequest passwordChangeRequest) {
		int clerkId = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

		PasswordChangeResponse passwordChangeResponse = clerkService.updatePassword(clerkId, passwordChangeRequest);
		return ResponseEntity.ok(passwordChangeResponse.getMessage());

	}

	@GetMapping("/customers")
	public ResponseEntity<?> findCustomers(@RequestParam(value = "search", defaultValue = "") String search,
			@RequestParam(value = "onlyMine", defaultValue = "false") boolean onlyMine,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit) {
		int clerkId = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		Page<Customer> customers = customerService.getCustomers(search, onlyMine, clerkId, page, limit);
		return ResponseEntity.ok(customers.map(CustomerResponseDTO::fromEntity));
	}

	@GetMapping("/services")
	public ResponseEntity<?> getServices(@RequestParam(value = "search", defaultValue = "") String search,
			@RequestParam(value = "onlyMine", defaultValue = "false") boolean onlyMine,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "custId", defaultValue = "") Integer custId,
			@RequestParam(value = "repairId", defaultValue = "") Integer repairId) {
		int clerkId = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		Page<RepairService> servicePage;
		if (custId != null) {
			servicePage = repairServiceService.getServicesForRole("customer", custId, false, search, page, limit);
		} else if (repairId != null) {
			servicePage = repairServiceService.getServicesForRole("repairperson", repairId, false, search, page, limit);
		} else {
			servicePage = repairServiceService.getServicesForRole("clerk", clerkId, onlyMine, search, page, limit);
		}

		return ResponseEntity.ok(servicePage.map(RepairServiceResponseDTO::fromEntity));
	}

	@GetMapping("/repairpersons")
	public ResponseEntity<?> getRepairPersons(@RequestParam(value = "search", defaultValue = "") String search,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "category", defaultValue = "") String specialty) {
		Page<RepairPerson> repairPersonPage = repairPersonService.findBySearch(search, page, limit, specialty);

		return ResponseEntity.ok(repairPersonPage.map(RepairPersonResponseDTO::fromEntity));
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<?> findCustomerById(@PathVariable("id") int id) {

		Customer customer = customerService.findById(id);
		return ResponseEntity.ok(CustomerResponseDTO.fromEntity(customer));

	}

	@GetMapping("/repairperson/{id}")
	public ResponseEntity<?> getProfile(@PathVariable("id") int repairId) {

		RepairPerson repairPerson = repairPersonService.findById(repairId);
		return ResponseEntity.ok(RepairPersonResponseDTO.fromEntity(repairPerson));

	}

	@GetMapping("/service/{id}")
	public ResponseEntity<?> getServiceById(@PathVariable("id") int id) {

		RepairService repairService = repairServiceService.findById(id);
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

	@PostMapping("/customer")
	public ResponseEntity<?> addCustomer(@RequestBody CustomerRequestDTO dto) {
		int clerkId = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		Customer customer = customerService.add(clerkId, dto);
		return ResponseEntity.ok(CustomerResponseDTO.fromEntity(customer));

	}
	
	@PostMapping("/service")
	public ResponseEntity<?> addRepairService(@RequestBody RepairServiceRequestDTO dto) {
		int clerkId = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		RepairService repairService = repairServiceService.add(clerkId, dto);
		return ResponseEntity.ok(RepairServiceResponseDTO.fromEntity(repairService));

	}
	
	@PostMapping("/service/{id}")
	public ResponseEntity<?> generateOTPForInvoice(@PathVariable("id") int id) {
		int clerkId = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		InvoiceGenerateResponse invoiceGenerateResponse = invoiceService.generateOTP(clerkId,id);
		return ResponseEntity.ok(invoiceGenerateResponse);

	}
	
	@PostMapping("/invoice/{id}")
	public ResponseEntity<?> generateInvoice(@PathVariable("id") int id, @RequestParam("otp") String otp){
		int clerkId = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		InvoiceResponse invoiceResponse = invoiceService.validateOTP(clerkId, id, otp);
		return ResponseEntity.ok(invoiceResponse);
	}

}
