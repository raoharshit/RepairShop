package com.repairshoptest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.repairshoptest.dto.EmailDetails;
import com.repairshoptest.dto.ForgotPasswordRequest;
import com.repairshoptest.dto.ForgotPasswordResponse;
import com.repairshoptest.exception.InvalidCredentialsException;
import com.repairshoptest.exception.ResourceNotFoundException;
import com.repairshoptest.model.OTP;
import com.repairshoptest.model.User;
import com.repairshoptest.service.OTPService;
import com.repairshoptest.service.UserService;
import com.repairshoptest.utils.OTPUtil;

@Service
public class ForgotPasswordService {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private OTPService otpService;
	
	@Autowired
	private OTPUtil otpUtil;
	

	public ForgotPasswordResponse generateOTP(String email) {
		if (email == null || email.isEmpty()) {
			throw new InvalidCredentialsException("Email shoul not be null");
		}
		User user = userService.findByEmail(email);

		if (user == null) {
			throw new ResourceNotFoundException("User not found");
		}

		OTP otp = otpUtil.generateOTP();

		EmailDetails emailDetails = new EmailDetails();

		emailDetails.setRecipient(user.getEmail());
		emailDetails.setSubject("Forgot password OTP");
		emailDetails
				.setMsgBody("Your otp for password change is: " + otp.getOtp() + ". It is valid for next 5 minutes.");

		emailService.sendSimpleMail(emailDetails);

		otpService.addForUser(user.getId(), otp);
		ForgotPasswordResponse response = new ForgotPasswordResponse();
		response.setId(user.getId());
		response.setMessage("OTP sent to user email");

		return response;
	}

	public ForgotPasswordResponse resendOTP(int id) {
		User user = userService.findById(id);

		if (user == null) {
			throw new ResourceNotFoundException("User not found");
		}

		OTP otp = otpUtil.generateOTP();

		EmailDetails emailDetails = new EmailDetails();

		emailDetails.setRecipient(user.getEmail());
		emailDetails.setSubject("Forgot password OTP");
		emailDetails
				.setMsgBody("Your otp for password change is: " + otp.getOtp() + ". It is valid for next 5 minutes.");

		emailService.sendSimpleMail(emailDetails);

		otpService.addForUser(user.getId(), otp);
		ForgotPasswordResponse response = new ForgotPasswordResponse();
		response.setId(user.getId());
		response.setMessage("OTP sent to user email");

		return response;
	}

	public String validateOTP(int id, String otp) {
		if (otp == null || otp.isEmpty()) {
			throw new InvalidCredentialsException("OTP cannot be empty");
		}
		User user = userService.findById(id);
		if (user == null) {
			throw new ResourceNotFoundException("User not found");
		}
		if (otpService.validateForUser(id, otp)) {
			return "Otp verified";
		}

		throw new InvalidCredentialsException("Incorrect otp");
	}

	public String changePassword(int id, ForgotPasswordRequest request) {
		if (!request.getNewPassword().equals(request.getConfirmPassword())) {
			throw new InvalidCredentialsException("Password are different");
		}

		User user = userService.findById(id);

		if (user == null) {
			throw new ResourceNotFoundException("User not found");
		}

		String hashedPassword = encoder.encode(request.getNewPassword());
		user.setHashedPassword(hashedPassword);
		User save = userService.add(user);
		if (save == null) {
			throw new RuntimeException("Some error occurred");
		}

		return "Password changed successfully";
	}

}