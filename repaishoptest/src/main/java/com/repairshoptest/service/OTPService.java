package com.repairshoptest.service;

import com.repairshoptest.model.OTP;

public interface OTPService {
	
	boolean validateForUser(int id, String otp);
	
	boolean validateForInvoice(int id, String otp);
	
	boolean addForUser(int id, OTP otp);
	
	boolean addForInvoice(int id, OTP otp);

}
