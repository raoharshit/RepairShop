package com.repairshoptest.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repairshoptest.dto.AdditionalItemRFAResponseDTO;
import com.repairshoptest.dto.InvoiceGenerateResponse;
import com.repairshoptest.dto.InvoiceResponse;
import com.repairshoptest.exception.InvalidCredentialsException;
import com.repairshoptest.exception.ResourceNotFoundException;
import com.repairshoptest.model.AdditionalItemRFA;
import com.repairshoptest.model.Clerk;
import com.repairshoptest.model.Invoice;
import com.repairshoptest.model.OTP;
import com.repairshoptest.model.RepairService;
import com.repairshoptest.repository.InvoiceRepo;
import com.repairshoptest.service.ClerkService;
import com.repairshoptest.service.InvoiceService;
import com.repairshoptest.service.OTPService;
import com.repairshoptest.service.RFAService;
import com.repairshoptest.service.RepairServiceService;
import com.repairshoptest.utils.OTPUtil;

@Service
public class InvoiceServiceImpl implements InvoiceService{
	
	@Autowired
	private InvoiceRepo invoiceRepo;
	
	@Autowired
	private RFAService rfaService;
	
	@Autowired
	private RepairServiceService repairServiceService;
	
	@Autowired
	private ClerkService clerkService;
	
	@Autowired
	private OTPService otpService;
	
	@Autowired
	private SmsService smsService;
	
	@Autowired
	private OTPUtil otpUtil;
	
	
	@Override
	public InvoiceGenerateResponse generateOTP(int clerkId,int serviceId) {
		Clerk clerk = clerkService.findById(clerkId);
		RepairService repairService = repairServiceService.findById(serviceId);
		if(clerk == null) {
			throw new ResourceNotFoundException("Clerk not found");
		}
		if(repairService == null) {
			throw new ResourceNotFoundException("Service not found");
		}
		Invoice invoice = invoiceRepo.findByRepairService(serviceId);
		OTP otp = otpUtil.generateOTP();
		InvoiceGenerateResponse response = new InvoiceGenerateResponse();
		int invoiceId;
		if(invoice == null) {
			invoiceId = generateInvoice(repairService);
			smsService.sendOtp(repairService.getCustomer().getPhone(), otp.getOtp());
			otpService.addForInvoice(invoiceId, otp);
			response.setId(invoiceId);
		}else {
			smsService.sendOtp(repairService.getCustomer().getPhone(), otp.getOtp());
			otpService.addForInvoice(invoice.getId(), otp);
			response.setId(invoice.getId());
		}
		
		response.setMessage("OTP sent to customer's mobile number");
		
		return response;
	}
	
	public int generateInvoice(RepairService service) {
		List<AdditionalItemRFA> list = rfaService.findByRepairServiceId(service.getId());
		double totalAmount = service.getBaseCharge();
		
		if(list != null && !list.isEmpty()) {
			for(AdditionalItemRFA rfa : list) {
				if(rfa.getApprovalStatus() == "Approved") {
					totalAmount += rfa.getServiceCharge() + rfa.getNewItem().getPrice();
				}
			}
		}
		
		Invoice invoice = new Invoice();
		invoice.setRepairService(service);
		invoice.setTotalAmount(totalAmount);
		
		Invoice save = invoiceRepo.save(invoice);
		
		if(save == null) {
			throw new RuntimeException("Some error occurred");
		}
		
		return save.getId();
		
	}
	
	@Override
	public InvoiceResponse validateOTP(int clerkId,int invoiceId, String otp) {
		if(otp == null || otp.isEmpty()) {
			throw new RuntimeException("Otp should not be empty");
		}
		Clerk clerk = clerkService.findById(clerkId);
		Optional<Invoice> optInvoice = invoiceRepo.findById(invoiceId);
		if(clerk == null) {
			throw new ResourceNotFoundException("Clerk is not found");
		}
		if(optInvoice.isEmpty()) {
			throw new ResourceNotFoundException("Invoice is not found");
		}
		

		Invoice invoice = optInvoice.get();
		
		if(otpService.validateForInvoice(invoiceId, otp)){
			invoice.setDeliveredAt(LocalDateTime.now());
			invoice.setDeliveredBy(clerk);
			invoice.setIsDelivered(true);
			Invoice save = invoiceRepo.save(invoice);
			if(save == null) {
				throw new RuntimeException("Some error occurred");
			}
			InvoiceResponse invoiceResponse = InvoiceResponse.getInvoiceResponse(invoice);
			List<AdditionalItemRFAResponseDTO> rfaList = new ArrayList<AdditionalItemRFAResponseDTO>();
			List<AdditionalItemRFA> list = rfaService.findByRepairServiceId(invoice.getRepairService().getId());
			if(list != null && !list.isEmpty()) {
				for(AdditionalItemRFA rfa: list) {
					if(rfa.getApprovalStatus() == "Approved") {
						rfaList.add(AdditionalItemRFAResponseDTO.fromEntity(rfa));
					}
				}
			}
			
			invoiceResponse.setList(rfaList);
			return invoiceResponse;
		}
		
		throw new InvalidCredentialsException("Otp is incorrect");
	}
}
