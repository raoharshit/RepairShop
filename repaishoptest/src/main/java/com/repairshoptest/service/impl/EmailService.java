package com.repairshoptest.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.repairshoptest.dto.EmailDetails;

@Service
public class EmailService{
	
	@Autowired
	private JavaMailSender javaMailSender;

	
	public String sendSimpleMail(EmailDetails details) {

		try {

			
			SimpleMailMessage mailMessage = new SimpleMailMessage();

			mailMessage.setTo(details.getRecipient());
			mailMessage.setText(details.getMsgBody());
			mailMessage.setSubject(details.getSubject());

		
			javaMailSender.send(mailMessage);
			return "Mail Sent Successfully...";
		}

		
		catch (Exception e) {
			e.printStackTrace();
			return "CustomError while Sending Mail";
		}
	}
	
}