package com.repairshoptest.utils;

import java.security.SecureRandom;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.repairshoptest.model.OTP;

@Component
public class OTPUtil {
	
	private static final int OTP_LENGTH = 6;
	private static final int EXPIRATION_MINUTES = 5;
	
	public static OTP generateOTP() {
		SecureRandom random = new SecureRandom();
		StringBuilder otp = new StringBuilder();
		for(int i=0; i<OTP_LENGTH; i++) {
			otp.append(random.nextInt(10));
		}
		LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(EXPIRATION_MINUTES);
		OTP newOtp = new OTP();
		newOtp.setOtp(otp.toString());
		newOtp.setExpirationTime(expirationTime);
		return newOtp;
	}

}
