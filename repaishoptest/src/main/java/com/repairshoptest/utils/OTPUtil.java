package com.repairshoptest.utils;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class OTPUtil {
	
	private static final int OTP_LENGTH = 6;
	
	public static String generateOTP() {
		SecureRandom random = new SecureRandom();
		StringBuilder otp = new StringBuilder();
		for(int i=0; i<OTP_LENGTH; i++) {
			otp.append(random.nextInt(10));
		}
		return otp.toString();
	}

}
