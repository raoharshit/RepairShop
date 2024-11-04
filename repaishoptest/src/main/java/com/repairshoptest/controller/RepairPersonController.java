package com.repairshoptest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

import com.repairshop.exception.InvalidCredentialsException;
import com.repairshop.exception.ResourceNotFoundException;
import com.repairshoptest.dto.AdditionalItemRFARequestDTO;
import com.repairshoptest.dto.AdditionalItemRFAResponseDTO;
import com.repairshoptest.dto.PasswordChangeRequest;
import com.repairshoptest.dto.PasswordChangeResponse;
import com.repairshoptest.dto.RepairPersonRequestDTO;
import com.repairshoptest.dto.RepairPersonResponseDTO;
import com.repairshoptest.dto.RepairServiceResponseDTO;
import com.repairshoptest.model.AdditionalItemRFA;
import com.repairshoptest.model.RepairPerson;
import com.repairshoptest.model.RepairService;
import com.repairshoptest.model.ServiceStatus;
import com.repairshoptest.service.RFAService;
import com.repairshoptest.service.RepairPersonService;
import com.repairshoptest.service.RepairServiceService;
import com.repairshoptest.service.ServiceStatusService;

@RestController
@RequestMapping("/repairperson")
public class RepairPersonController {
	
	@Autowired
	private RepairPersonService repairPersonService;
	
	@Autowired
	private RepairServiceService repairServiceService;
	
	@Autowired
	private RFAService rfaService;
	
	@Autowired
	private ServiceStatusService serviceStatusService;
	
	@GetMapping("/profile")
	public ResponseEntity<?> getProfile(){
		int repairId = Integer.parseInt((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		try {
			RepairPerson repairPerson = repairPersonService.findById(repairId);
			return ResponseEntity.ok(RepairPersonResponseDTO.fromEntity(repairPerson));
		}catch(ResourceNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@PutMapping("/profile")
	public ResponseEntity<?> updateProfile(@RequestBody RepairPersonRequestDTO repairPersonRequestDTO) {
		int repairId = Integer.parseInt((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		try {
			RepairPerson repairPerson = repairPersonService.update(repairId, repairPersonRequestDTO);
			return ResponseEntity.ok(RepairPersonResponseDTO.fromEntity(repairPerson));
		}catch(ResourceNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping("/password")
	public ResponseEntity<?> updatePassword(@RequestBody PasswordChangeRequest passwordChangeRequest) {
		int repairId = Integer.parseInt((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		try {
			PasswordChangeResponse passwordChangeResponse = repairPersonService.updatePassword(repairId, passwordChangeRequest);
			return ResponseEntity.ok(passwordChangeResponse);
		}catch(ResourceNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}catch(InvalidCredentialsException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@GetMapping("/services")
	public ResponseEntity<?> getServices(@RequestParam(value = "search", defaultValue = "") String search, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value="limit",defaultValue="10")int limit) {
		int repairId = Integer.parseInt((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		Page<RepairService> servicePage = repairServiceService.getServicesForRole("repairperson", repairId, false, search, page, limit);
		
		return ResponseEntity.ok(servicePage.map(RepairServiceResponseDTO::fromEntity));
	}
	
	@GetMapping("/requests")
	public ResponseEntity<?> getRequests(@RequestParam(value = "search", defaultValue = "") String search, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value="limit",defaultValue="10")int limit) {
		int repairId = Integer.parseInt((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		Page<AdditionalItemRFA> rfaPage = rfaService.findRFAForRole("repairperson", repairId, search, page, limit);
		
		return ResponseEntity.ok(rfaPage.map(AdditionalItemRFAResponseDTO::fromEntity));
	}
	
	@GetMapping("/request/{id}")
	public ResponseEntity<?> getRequestById(@PathVariable("id") int id) {
		try {
			AdditionalItemRFA rfa = rfaService.findById(id);
			return ResponseEntity.ok(AdditionalItemRFAResponseDTO.fromEntity(rfa));
		}catch(ResourceNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/service/{id}")
	public ResponseEntity<?> getServiceById(@PathVariable("id") int id) {
		try {
			RepairService repairService = repairServiceService.findById(id);
			return ResponseEntity.ok(RepairServiceResponseDTO.fromEntity(repairService));
		}catch(ResourceNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
		
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
	
	@PostMapping("/service/{id}/request")
	public ResponseEntity<?> createRequest(@PathVariable("id") int id, @RequestBody AdditionalItemRFARequestDTO dto){
		try {
			AdditionalItemRFA rfa = rfaService.createRFA(id, dto);
			return ResponseEntity.ok(AdditionalItemRFAResponseDTO.fromEntity(rfa));
		}catch(ResourceNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}catch(Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping("/service/{id}/close")
	public ResponseEntity<?> closeRepairService(@PathVariable("id") int id) {
		
		try {
			boolean closeService = repairServiceService.closeService(id);
			return ResponseEntity.ok("Service closed successfully");
		}catch(ResourceNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}catch(Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
//	@GetMapping("/byid")
//	public RepairPerson findRepairPersonById(@RequestHeader(value = "repairId") int repairId) {
//		RepairPerson repairPerson = repairPersonService.findById(repairId);
//		return repairPerson;
//	}
//	
//	@GetMapping("/byemail")
//	public RepairPerson findRepairPersonByEmail(@RequestParam(value = "email") String email) {
//		RepairPerson repairPerson = repairPersonService.findByEmail(email);
//		return repairPerson;
//	}
//	
//	@PostMapping("/addRepairPerson")
//	public RepairPerson addRepairPerson(@RequestBody RepairPersonRequestDTO repairPersonRequestDTO) {
//		RepairPerson repairPerson = repairPersonService.add(repairPersonRequestDTO);
//		return repairPerson;
//	}
//	
//	@PostMapping("/updateRepairPerson")
//	public RepairPerson updateRepairPerson(@RequestHeader("repairId") int repairId, @RequestBody RepairPersonRequestDTO repairPersonRequestDTO) {
//		RepairPerson repairPerson = repairPersonService.update(repairId,repairPersonRequestDTO);
//		return repairPerson;
//	}

}
