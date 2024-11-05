package com.repairshoptest.service;

import com.repairshoptest.dto.InvoiceGenerateResponse;
import com.repairshoptest.dto.InvoiceResponse;

public interface InvoiceService {
	
	InvoiceGenerateResponse generateOTP(int clerkId, int serviceId);
	InvoiceResponse validateOTP(int clerkId,int invoiceId, String otp);
	

}
