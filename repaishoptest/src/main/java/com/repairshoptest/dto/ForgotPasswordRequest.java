package com.repairshoptest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ForgotPasswordRequest {
	
	@NotNull(message = "newpassword cannot be null")
    @NotBlank(message = "newpassword cannot be blank")
	private String newPassword;
	
	@NotNull(message = "confirmpassword cannot be null")
    @NotBlank(message = "confirmpassword cannot be blank")
	private String confirmPassword;
	
	
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	

}