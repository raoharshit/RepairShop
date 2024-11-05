package com.repairshoptest.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Service
public class SmsService {
	
	@Value("${twilio.account.sid}")
	private String accountSid;
	
	@Value("${twilio.auth.token}")
	private String authToken;
	
	@Value("${twilio.phone.number}")
	private String fromNumber;
	
	public void sendOtp(String toNumber, String otp) {
		Twilio.init(accountSid, authToken);
		
		
		Message message = Message.creator(new com.twilio.type.PhoneNumber(toNumber),
				new com.twilio.type.PhoneNumber(fromNumber),
		"Your otp code is: " + otp).create();
		
	}

}
