package com.repairshoptest.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repairshoptest.exception.InvalidCredentialsException;
import com.repairshoptest.exception.ResourceNotFoundException;
import com.repairshoptest.model.OTP;
import com.repairshoptest.repository.OTPRepo;
import com.repairshoptest.service.OTPService;
@Service
public class OTPServiceImpl implements OTPService{

	@Autowired
	private OTPRepo otpRepo;
	
	@Override
	public boolean validateForUser(int id, String otp) {
		List<OTP> listOTP = otpRepo.findTopByUserIdOrderByCreatedAtDesc(id);
		 OTP saved = listOTP.get(0);
		if(saved == null) {
			throw new ResourceNotFoundException("OTP not found");
		}
		if(checkExpired(saved)) {
			throw new InvalidCredentialsException("OTP is expired");
		}
		
		if(saved.getOtp().equals(otp)) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean validateForInvoice(int id, String otp) {
		List<OTP> listOTP = otpRepo.findTopByInvoiceIdOrderByCreatedAtDesc(id);
		OTP saved = listOTP.get(0);
		if(saved == null) {
			throw new ResourceNotFoundException("OTP not found");
		}
		if(checkExpired(saved)) {
			throw new InvalidCredentialsException("OTP is expired");
		}
		
		if(saved.getOtp().equals(otp)) {
			return true;
		}
		return false;
	}


	@Override
	public boolean addForUser(int id, OTP otp) {
		otp.setUserId(id);
		OTP save = otpRepo.save(otp);
		if(save == null) {
			throw new RuntimeException("Some error occurred");
		}
		return true;
	}

	@Override
	public boolean addForInvoice(int id, OTP otp) {
		otp.setInvoiceId(id);
		OTP save = otpRepo.save(otp);
		if(save == null) {
			throw new RuntimeException("Some error occurred");
		}
		return true;
	}
	
private boolean checkExpired(OTP saved) {
		
		if(LocalDateTime.now().isBefore(saved.getExpirationTime())) {
			return false;
		}
		
		return true;
	}

	

}
