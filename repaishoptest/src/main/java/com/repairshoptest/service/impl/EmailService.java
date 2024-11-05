package com.repairshoptest.service.impl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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